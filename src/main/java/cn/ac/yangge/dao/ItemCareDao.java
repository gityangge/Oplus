package cn.ac.yangge.dao;

import java.util.List;

import cn.ac.yangge.pojo.ItemCare;
import cn.ac.yangge.pojo.ItemCareDetail;

public interface ItemCareDao {
	public int insertItemCare(ItemCare itemCare);
	public int updateItemCare(ItemCare itemCare);
	public ItemCare getItemCare(int itemCareID);
	public int insertItemCareDetail(ItemCareDetail itemCaredetail);
	public int deleteItemCareDetail(ItemCareDetail itemCareDetail);
	public ItemCareDetail getCareDetail(ItemCareDetail itemCareDetail);
	public List<ItemCareDetail> getUserCareList(String uniteID);
}
