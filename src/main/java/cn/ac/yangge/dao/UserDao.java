package cn.ac.yangge.dao;
import java.util.List;
import cn.ac.yangge.pojo.User;

public interface UserDao {
	public User getUserByUniteID(String uniteID);
	public int insertUser(User user);
	public int deleteUserByUniteID(String uniteID);
	public int updateUser(User user);
	public List<User> getUserByUserName(String userName);
	public List<User> getUserByTel(String tel);
}
