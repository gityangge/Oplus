package cn.ac.yangge.service;


import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.ac.yangge.dao.UserDao;
import cn.ac.yangge.dao.UserPowerDao;
import cn.ac.yangge.pojo.User;
import cn.ac.yangge.pojo.UserPower;
import cn.ac.yangge.tool.*;

@Service("userService")
public class UserService {
	
	@Resource
	private UserDao userDao;
	@Resource
	private UserPowerDao userPowerDao;
	public int insertUser(String tel,String password){
		User user=new User();
		user.setUserName("手机用户"+tel);
		user.setUserTel(tel);
		user.setUserType(1);
		long time=System.currentTimeMillis();
		user.setUserTime(time);
		/* 生成uniteID */
		String uniteID=MD5Util.md5Encode16("yangge"+time);
		user.setUniteID(uniteID);
		/* 初始化能力值  */
		if(userPowerDao.insertUserPower(uniteID)!=1){
			return 0;
		}
		/* 加密密码 */
		password=MD5Util.md5Encode16(password);
		user.setPassword(password);
		/***  同步到IM  ***/
		int result=IMPost.newIMUser(uniteID, "手机用户"+tel, "http://ofostowfr.bkt.clouddn.com/image/headimg/01.jpg");
		if(result==0){
			return userDao.insertUser(user);			
		}else{
			System.out.println("im post error "+result);
			return result;
		}
	}
	public boolean isOKUserName(String userName){
		if(!userName.isEmpty()&&userDao.getUserByUserName(userName).isEmpty()){
			return true;
		}else{
			return false;
		}
	}
	public User findUserByUniteID(String uniteID){
		return userDao.getUserByUniteID(uniteID);
	}
	public User findUserByTel(String tel){
		List<User> userList=userDao.getUserByTel(tel);
		if(userList.isEmpty()){
			return null;
		}else{
			return userList.get(0);
		}
	}
	public UserPower findUserPower(String uniteID){
		return userPowerDao.findUserPower(uniteID);
	}
	public int updatapwd(String tel,String password){
		User user=findUserByTel(tel);
		if(user!=null){
			if(!(password.length()<3||password.length()>16)){
				user.setPassword(MD5Util.md5Encode16(password));
				return updateUser(user);				
			}else{
				return -1;//pwd illegle
			}
		}else{
			return -2;//tel not find
		}
	}
	public int updateUserPower(String uniteID,int type,int value){
		UserPower userPower=userPowerDao.findUserPower(uniteID);
		if(userPower!=null){
			if(type==0){
				userPower.setUserPower(userPower.getUserPower()+value);
				if(userPowerDao.updateUserPower(userPower)==1){
					return 1;
				}else{
					return 0;
				}
			}else if(type==1){
				userPower.setUserActive(userPower.getUserActive()+value);
				if(userPowerDao.updateUserPower(userPower)==1){
					return 1;
				}else{
					return 0;
				}
			}else{
				return 2;
			}
		}else{
			return 3;
		}
	}
	public int deleteUser(String uniteID){
		return userDao.deleteUserByUniteID(uniteID);
	}
	public int updateUser(User user){
		return userDao.updateUser(user);
	}
}
