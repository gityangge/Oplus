package cn.ac.yangge.dao;

import cn.ac.yangge.pojo.ItemMemberState;

public interface ItemMemberStateDao {
	public int insertMemberState(ItemMemberState itemMemberState);
	public int updateMemberState(ItemMemberState itemMemberState);
	public ItemMemberState getMemberState(int memberStateID);
	public int deleteMemberState(int memberStateID);
}
