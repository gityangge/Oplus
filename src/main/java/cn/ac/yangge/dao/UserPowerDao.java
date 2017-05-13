package cn.ac.yangge.dao;

import cn.ac.yangge.pojo.UserPower;

public interface UserPowerDao {
	public int insertUserPower(String uniteID);
	public int updateUserPower(UserPower userPower);
	public UserPower findUserPower(String uniteID);
}
