package cn.ac.yangge.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.ac.yangge.dao.ItemCareDao;
import cn.ac.yangge.dao.ItemDao;
import cn.ac.yangge.dao.ItemLoveDao;
import cn.ac.yangge.dao.UserDao;
import cn.ac.yangge.pojo.ArraryHelp;
import cn.ac.yangge.pojo.Item;
import cn.ac.yangge.pojo.ItemCare;
import cn.ac.yangge.pojo.ItemCareDetail;
import cn.ac.yangge.pojo.ItemLove;
import cn.ac.yangge.pojo.ItemLoveDetail;
import cn.ac.yangge.tool.ObjectAndJson;

@Service("LoveCareService")
public class LoveCareService {
@Resource
ItemLoveDao itemLoveDao;
@Resource
ItemCareDao itemCareDao;
@Resource
UserDao userDao;
@Resource
ItemDao itemDao;
public int addLove(ItemLoveDetail itemLoveDetail){
	if(itemLoveDetail.getUniteID().isEmpty()||userDao.getUserByUniteID(itemLoveDetail.getUniteID())==null){
		return 2;//uniteID ERR
	}else if(itemLoveDetail.getItemID()==0){
		return 3;//itemID ERR
	}else{
		Item item=itemDao.getItemByID(itemLoveDetail.getItemID());
		if(item==null){
			return 3;
		}
		
		
		ItemLove itemLove=itemLoveDao.getItemLove(item.getItemLoveListID());
		String oldJson=itemLove.getWhoLoveList();
		List<String> pList;
		if(oldJson.isEmpty()){
			pList=new ArrayList<String>();
		}else{
			pList =ObjectAndJson.JsonToObject(itemLove.getWhoLoveList()).getList();
		}
		int isExsit=0;
		for(int i=0;i<pList.size();i++){
			if(pList.get(i).equals(itemLoveDetail.getUniteID())){
				isExsit=1;
				break;
			}
		}
		if(isExsit==1){
			return 5;
		}else{
			pList.add(itemLoveDetail.getUniteID());
			ArraryHelp listHelp=new ArraryHelp();
			listHelp.setList(pList);
			itemLove.setWhoLoveList(ObjectAndJson.ObjectToJson(listHelp));
			if(itemLoveDao.updateItemLove(itemLove)!=1){
				return 4;//item love update failue
			}
		}
		
		itemLoveDetail.setLoveTime(System.currentTimeMillis());
		if(itemDao.addLoveNum(itemLoveDetail)==1){
			return itemLoveDao.insertItemLoveDetail(itemLoveDetail);
		}else{
			return 6;
		}
		
	}
	
}

public int removeLove(ItemLoveDetail itemLoveDetail){
	if(itemLoveDetail.getUniteID().isEmpty()||userDao.getUserByUniteID(itemLoveDetail.getUniteID())==null){
		return 2;//uniteID ERR
	}else if(itemLoveDetail.getItemID()==0){
		return 3;//itemID ERR
	}else{
		Item item=itemDao.getItemByID(itemLoveDetail.getItemID());
		if(item==null){
			return 3;
		}else{
			ItemLove itemLove=itemLoveDao.getItemLove(itemDao.getItemByID(itemLoveDetail.getItemID()).getItemLoveListID());
			String oldJson=itemLove.getWhoLoveList();
			List<String> pList;
			if(oldJson.isEmpty()){
				pList=new ArrayList<String>();
			}else{
				pList =ObjectAndJson.JsonToObject(itemLove.getWhoLoveList()).getList();
			}
			if(!pList.remove(itemLoveDetail.getUniteID())){
				return 4;//remove falue
			}else{
				ArraryHelp listHelp=new ArraryHelp();
				listHelp.setList(pList);
				itemLove.setWhoLoveList(ObjectAndJson.ObjectToJson(listHelp));
				itemLoveDao.updateItemLove(itemLove);
			}
			
		}
		if(itemDao.removeLoveNum(itemLoveDetail)==1){
			return itemLoveDao.deleteItemLoveDetail(itemLoveDetail);
		}else{
			return 5;
		}
		
	}
}

public int addCare(ItemCareDetail itemCareDetail){
	if(itemCareDetail.getUniteID().isEmpty()||userDao.getUserByUniteID(itemCareDetail.getUniteID())==null){
		return 2;//uniteID ERR
	}else if(itemCareDetail.getItemID()==0){
		return 3;//itemID ERR
	}else{
		Item item=itemDao.getItemByID(itemCareDetail.getItemID());
		if(item==null){
			return 3;
		}
		
		ItemCare itemCare=itemCareDao.getItemCare(item.getItemCareListID());
		String oldJson=itemCare.getWhoCareList();
		List<String> pList;
		if(oldJson.isEmpty()){
			pList=new ArrayList<String>();
		}else{
			pList =ObjectAndJson.JsonToObject(itemCare.getWhoCareList()).getList();
		}
		int isExsit=0;
		for(int i=0;i<pList.size();i++){
			if(pList.get(i).equals(itemCareDetail.getUniteID())){
				isExsit=1;
				break;
			}
		}
		if(isExsit==1){
			return 5;
		}else{
			pList.add(itemCareDetail.getUniteID());
			ArraryHelp listHelp=new ArraryHelp();
			listHelp.setList(pList);
			itemCare.setWhoCareList(ObjectAndJson.ObjectToJson(listHelp));
			if(itemCareDao.updateItemCare(itemCare)!=1){
				return 4;//item love update failue
			}
		}
		
		itemCareDetail.setCareTime(System.currentTimeMillis());
		if(itemDao.addCareNum(itemCareDetail)==1){
			return itemCareDao.insertItemCareDetail(itemCareDetail);
		}else{
			return 6;
		}
		
	}
	
}

public int removeCare(ItemCareDetail itemCareDetail){
	if(itemCareDetail.getUniteID().isEmpty()||userDao.getUserByUniteID(itemCareDetail.getUniteID())==null){
		return 2;//uniteID ERR
	}else if(itemCareDetail.getItemID()==0){
		return 3;//itemID ERR
	}else{
		Item item=itemDao.getItemByID(itemCareDetail.getItemID());
		if(item==null){
			return 3;
		}else{
			ItemCare itemCare=itemCareDao.getItemCare(itemDao.getItemByID(itemCareDetail.getItemID()).getItemCareListID());
			String oldJson=itemCare.getWhoCareList();
			List<String> pList;
			if(oldJson.isEmpty()){
				pList=new ArrayList<String>();
			}else{
				pList =ObjectAndJson.JsonToObject(itemCare.getWhoCareList()).getList();
			}
			if(!pList.remove(itemCareDetail.getUniteID())){
				return 4;//remove falue
			}else{
				ArraryHelp listHelp=new ArraryHelp();
				listHelp.setList(pList);
				itemCare.setWhoCareList(ObjectAndJson.ObjectToJson(listHelp));
				itemCareDao.updateItemCare(itemCare);
			}
			
		}
		if(itemDao.removeCareNum(itemCareDetail)==1){
			return itemCareDao.deleteItemCareDetail(itemCareDetail);
		}else{
			return 5;
		}
		
	}
}
}
