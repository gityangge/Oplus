package cn.ac.yangge.dao;

import cn.ac.yangge.pojo.ItemMemberStateOther;

public interface ItemMemberStateOtherDao {
	public int insertMemberStateOther(ItemMemberStateOther itemMemberStateOther);
	public int updateMemberStateOther(ItemMemberStateOther itemMemberStateOther);
	public ItemMemberStateOther getMemberStateOther(int memberStateOtherID);
	public int deleteMemberStateOther(int memberStateOtherID);
}
