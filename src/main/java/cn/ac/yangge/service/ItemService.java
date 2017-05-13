package cn.ac.yangge.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.ac.yangge.dao.ItemCareDao;
import cn.ac.yangge.dao.ItemDao;
import cn.ac.yangge.dao.ItemLoveDao;
import cn.ac.yangge.dao.ItemMemberStateDao;
import cn.ac.yangge.pojo.Item;
import cn.ac.yangge.pojo.ItemCare;
import cn.ac.yangge.pojo.ItemContent;
import cn.ac.yangge.pojo.ItemLove;
import cn.ac.yangge.pojo.ItemMemberState;

@Service("itemService")
public class ItemService {
	@Resource
	private ItemDao itemDao;
	@Resource
	TeamService teamService;
	@Resource
	ItemMemberStateDao itemMemberStateDao;
	@Resource
	ItemLoveDao itemLoveDao;
	@Resource
	ItemCareDao itemCareDao;
	@Resource
	UserService userService;
	public Item getItemByID(int itemID){
		return itemDao.getItemByID(itemID);
	}
	public List<Item> getItemByName(String itemName){
		return itemDao.getItemByItemName(itemName);
	}
	public int insertItem(Item item){
		if(item.getItemName().isEmpty()){
			return 2;
		}else if(item.getItemLeader().isEmpty()){
			return 3;
		}else if(!isOKItemName(item.getItemName())){
			return 4;
		}else if(userService.findUserByUniteID(item.getItemLeader())==null){
			return 6;//uniteID cannot find
		}else if(item.getItemType()==0){
			return 9;//itemType have be true
		}else{
			item.setItemTime(System.currentTimeMillis());
			if(item.getItemTeam()==-1){
				item.setItemTeam(0);
			}else{
				if(teamService.findTeamByID(item.getItemTeam())==null){
					return 5;//团队号无效
				}else{
					item.setItemTeam(item.getItemTeam());
				}
			}
			if(item.getItemNeedMember()==-1||item.getItemNeedMember()==0){
				item.setItemNeedMember(0);
			}else{
				/* 定义成员需求 */
				ItemMemberState itemMemberState=item.getItemMemberState();
				if(itemMemberState==null){
					return 8;//成员列表找不到
				}else{
					ItemMemberState tempMemberState=new ItemMemberState();
					tempMemberState.initMemberState();
					itemMemberState=checkMemberState(itemMemberState, tempMemberState);
				}
				
				itemMemberStateDao.insertMemberState(itemMemberState);
				if(itemMemberState.getMemberStateID()>0){
					item.setItemNeedMember(itemMemberState.getMemberStateID());					
				}else{
					return 7;
				}
				/* 成员需求定义结束 */
			}
			/* 筛选其他项目状态
			 * 如果itemState==8,则为自定义状态，itemStateOther有效
			 * 否则无效 */
			if(item.getItemState()!=8){
				item.setItemStateOther("");
			}
			/* 对项目介绍处理 */
			String content;
			if(item.getItemContent().length()>75){
				content=item.getItemContent().substring(0,75);				
			}else{
				content=item.getItemContent();
			}
			ItemContent itemContent=new ItemContent();
			itemContent.setItemContent(item.getItemContent());
			itemDao.insertContent(itemContent);
			int contentID=itemContent.getItemContentID();
			if(contentID>0){
				item.setItemContent(content);
				item.setItemContentID(contentID);
			}else{
				return 0;
			}
			/* 定义点赞 */
			ItemLove  itemLove = new ItemLove();
			itemLove.setWhoLoveList("");
			itemLoveDao.insertItemLove(itemLove);
			if(itemLove.getLoveID()>0){
				item.setItemLoveListID(itemLove.getLoveID());
			}else{
				return 7;
			}
			/* 定义关注  */
			ItemCare itemCare=new ItemCare();
			itemCare.setWhoCareList("");
			itemCareDao.insertItemCare(itemCare);
			if(itemCare.getCareID()>0){
				item.setItemCareListID(itemCare.getCareID());
			}else{
				return 7;
			}
			
			int result=itemDao.insertItem(item);
			if(result==0){
				return 0;
			}else{
				return 1;
			}
		}

	}
	public boolean isOKItemName(String itemName){
		if(itemDao.getItemByItemName(itemName).isEmpty()){
			return true;
		}else{
			return false;
		}
	}
	public int deleteItem(int itemID){
		return itemDao.deleteItemByID(itemID);
	}
	public int updateItem(Item item){
		Item tempItem=new Item();
		tempItem=getItemByID(item.getItemID());
		if(tempItem.getItemType()==0){
			return 2;//itemID not exsit
		}
		if(item.getItemName().isEmpty()){
			item.setItemName(tempItem.getItemName());
		}
		if(isOKItemName(item.getItemName())||item.getItemName().equals(tempItem.getItemName())){
			if(item.getItemType()==0){
				item.setItemType(tempItem.getItemType());
			}
			if(item.getItemContent().isEmpty()){
				item.setItemContent(tempItem.getItemContent());
			}else{
				/* 对项目介绍处理 */
				String content;
				if(item.getItemContent().length()>75){
					content=item.getItemContent().substring(0,75);				
				}else{
					content=item.getItemContent();
				}
				ItemContent itemContent=new ItemContent();
				itemContent.setItemContent(item.getItemContent());
				itemDao.insertContent(itemContent);
				int contentID=itemContent.getItemContentID();
				if(contentID>0){
					item.setItemContent(content);
					item.setItemContentID(contentID);
				}else{
					return 0;
				}
			}
			if(item.getItemLeader().isEmpty()){
				item.setItemLeader(tempItem.getItemLeader());
			}
			if(item.getItemTeam()==-1){
				item.setItemTeam(tempItem.getItemTeam());
			}
			if(item.getItemState()==0){
				item.setItemState(tempItem.getItemState());
			}
			/* 对项目需求成员的更新  */
			if(item.getItemNeedMember()==-1){
				item.setItemNeedMember(tempItem.getItemNeedMember());
			}else if(item.getItemNeedMember()==0){
				/* 先判断之前的needMember在state里面是否有注册  */
				if(tempItem.getItemNeedMember()>0){
					itemMemberStateDao.deleteMemberState(tempItem.getItemNeedMember());
				}
			}else{
				/* 筛选无用自定义项目状态 */
				if(item.getItemState()==8&&item.getItemStateOther().isEmpty()){
					item.setItemStateOther(tempItem.getItemStateOther());
				}
				
				
				/* 定义成员需求 */
				ItemMemberState itemMemberState=item.getItemMemberState();
				if(itemMemberState==null){
					return 4;//成员需求列表找不到
				}else{	
					if(tempItem.getItemNeedMember()==0||tempItem.getItemNeedMember()==-1){
						ItemMemberState tempMemberState=new ItemMemberState();
						tempMemberState.initMemberState();
						itemMemberState=checkMemberState(itemMemberState, tempMemberState);
					}else{
						ItemMemberState oldMemberState=itemMemberStateDao.getMemberState(tempItem.getItemNeedMember());
						itemMemberState=checkMemberState(itemMemberState, oldMemberState);
					}
					int result=itemMemberStateDao.updateMemberState(itemMemberState);
					if(result!=1){
						return 5;//sql err
					}
				}
			}
			/* 对项目需求成员的更新 结束 */
			item.setItemCareNum(tempItem.getItemCareNum());
			item.setItemCareListID(tempItem.getItemCareListID());
			item.setItemLoveNum(tempItem.getItemLoveNum());
			item.setItemLoveListID(tempItem.getItemLoveListID());
			
			return itemDao.updateItem(item);
		}else{
			return 3;//itemName illegle
		}
		
	}
	public List<Item> getItemList(){
		List<Item> itemList=itemDao.getItemList();
		for(Item item:itemList){
			if(item.getItemNeedMember()>0){
				ItemMemberState memberState=itemMemberStateDao.getMemberState(item.getItemNeedMember());
				if(memberState!=null){
					item.setItemMemberState(memberState);
				}else{
					item.setItemMemberState(null);
				}
			}else{
				item.setItemMemberState(null);
			}
		}
		return itemList;
	}
	public ItemContent getDetailContent(int itemContentID){
		ItemContent itemContent=itemDao.getContent(itemContentID);
		if(itemContent==null){
			itemContent=new ItemContent();
		}
		return itemContent;
	}
	private ItemMemberState checkMemberState(ItemMemberState itemMemberState,ItemMemberState oldMemberState){
		if(itemMemberState.getMemberNeedAndroid()==-1){
			itemMemberState.setMemberNeedAndroid(oldMemberState.getMemberNeedAndroid());
		}
		if(itemMemberState.getMemberNeediOS()==-1){
			itemMemberState.setMemberNeediOS(oldMemberState.getMemberNeediOS());
		}
		if(itemMemberState.getMemberNeedBack()==-1){
			itemMemberState.setMemberNeedBack(oldMemberState.getMemberNeedBack());
		}
		if(itemMemberState.getMemberNeedWeb()==-1){
			itemMemberState.setMemberNeedWeb(oldMemberState.getMemberNeedWeb());
		}
		if(itemMemberState.getMemberNeedManager()==-1){
			itemMemberState.setMemberNeedManager(oldMemberState.getMemberNeedManager());
		}
		if(itemMemberState.getMemberNeedOther().isEmpty()){
			itemMemberState.setMemberNeedOther(oldMemberState.getMemberNeedOther());
		}
		return itemMemberState;
	}
}
