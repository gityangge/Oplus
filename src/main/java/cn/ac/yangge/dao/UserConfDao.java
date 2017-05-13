package cn.ac.yangge.dao;

import java.util.List;

import cn.ac.yangge.pojo.UserConf;

public interface UserConfDao {
	public int insertUserConf(UserConf conf);
	public UserConf getUserConfByID(int confID);
	public int deleteUserConfByID(int confID);
	public List<UserConf> getUserConfByTel(String tel);
	public int updateCode(UserConf conf);
}
