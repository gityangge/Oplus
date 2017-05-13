package cn.ac.yangge.dao;

import java.util.List;

import cn.ac.yangge.pojo.Item;
import cn.ac.yangge.pojo.ItemCareDetail;
import cn.ac.yangge.pojo.ItemContent;
import cn.ac.yangge.pojo.ItemLoveDetail;

public interface ItemDao {
	public Item getItemByID(int itemID);
	public List<Item> getItemList();
	public List<Item> getItemByItemName(String itemName);
	public int updateItem(Item item);
	public int deleteItemByID(int itemID);
	public int insertItem(Item item);
	public int addLoveNum(ItemLoveDetail itemLoveDetail);
	public int removeLoveNum(ItemLoveDetail itemLoveDetail);
	public int addCareNum(ItemCareDetail itemCareDetail);
	public int removeCareNum(ItemCareDetail itemCareDetail);
	public int insertContent(ItemContent itemContent);
	public ItemContent getContent(int itemContentID);
	public int updateContent(ItemContent itemContent);
}
