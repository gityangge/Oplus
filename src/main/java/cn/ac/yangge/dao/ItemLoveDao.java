package cn.ac.yangge.dao;

import java.util.List;

import cn.ac.yangge.pojo.ItemLove;
import cn.ac.yangge.pojo.ItemLoveDetail;

public interface ItemLoveDao {
	public int insertItemLove(ItemLove itemLove);
	public int updateItemLove(ItemLove itemLove);
	public ItemLove getItemLove(int itemLoveID);
	public int insertItemLoveDetail(ItemLoveDetail itemLovedetail);
	public int deleteItemLoveDetail(ItemLoveDetail itemLoveDetail);
	public ItemLoveDetail getLoveDetail(ItemLoveDetail itemLoveDetail);
	public List<ItemLoveDetail> getUserLoveList(String uniteID);
}
