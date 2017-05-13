package cn.ac.yangge.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.ac.yangge.dao.UserConfDao;
import cn.ac.yangge.dao.UserDao;
import cn.ac.yangge.pojo.UserConf;
import cn.ac.yangge.tool.MessageTool;
import cn.ac.yangge.tool.PhoneFormatCheckUtils;

@Service("userConfService")
public class UserConfService {
	@Resource
	private UserConfDao userConfDao;
	@Resource
	private UserDao userDao;
	public int newUserConf(UserConf userConf){
		String tel=userConf.getTel();
		String password=userConf.getPassword();
		if(tel.isEmpty()){
			return -1;//手机号不能为空
		}else if(!PhoneFormatCheckUtils.isPhoneLegal(tel)){
			return -2;//手机号非法
		}else if(password.length()<3||password.length()>16){
			return -3;//密码长度错误
		}else if(!userDao.getUserByTel(tel).isEmpty()){
			return -4;//已经注册过了
		}else{
			if(userConfDao.getUserConfByTel(tel).isEmpty()){
				long time=System.currentTimeMillis();
				int code=((int)(time%10000)*2+1024)%10000;
				if(code<1000){
					code+=1000;
				}
				userConf.setCode(code);
				userConf.setTime(time);
				int ret=MessageTool.messageTool(tel, code);
				
				int result=userConfDao.insertUserConf(userConf);
				return userConf.getConfID();//success				

			}else{
				long time=System.currentTimeMillis();
				int code=((int)(time%10000)*2+1024)%10000;
				if(code<1000){
					code+=1000;
				}
				userConf.setCode(code);
				int ret=MessageTool.messageTool(tel, code);
				int result=userConfDao.updateCode(userConf);
				if(result!=0){
					return userConfDao.getUserConfByTel(tel).get(0).getConfID();
				}else{
					return 0;
				}
			}
		}
	}
	public int newMissingUserConf(UserConf userConf){
		String tel=userConf.getTel();
		String password=userConf.getPassword();
		if(tel.isEmpty()){
			return -1;//手机号不能为空
		}else if(!PhoneFormatCheckUtils.isPhoneLegal(tel)){
			return -2;//手机号非法
		}else if(userDao.getUserByTel(tel).isEmpty()){
			return -3;//没有注册过
		}else{
			if(userConfDao.getUserConfByTel(tel).isEmpty()){
				long time=System.currentTimeMillis();
				int code=((int)(time%10000)*2+1024)%10000;
				if(code<1000){
					code+=1000;
				}
				userConf.setCode(code);
				userConf.setTime(time);
				int ret=MessageTool.messageTool(tel, code);
				
				int result=userConfDao.insertUserConf(userConf);
				return userConf.getConfID();//success				

			}else{
				long time=System.currentTimeMillis();
				int code=((int)(time%10000)*2+1024)%10000;
				if(code<1000){
					code+=1000;
				}
				userConf.setCode(code);
				int ret=MessageTool.messageTool(tel, code);
				int result=userConfDao.updateCode(userConf);
				if(result!=0){
					return userConfDao.getUserConfByTel(tel).get(0).getConfID();
				}else{
					return 0;
				}
			}
		}	
	}
	public UserConf findUserConfByID(int confID){
		return userConfDao.getUserConfByID(confID);
	}
	public int deleteUserConf(int confID){
		return userConfDao.deleteUserConfByID(confID);
	}
}
