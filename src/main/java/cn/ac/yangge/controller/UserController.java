package cn.ac.yangge.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.json.JsonObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mysql.jdbc.CachedResultSetMetaData;

import cn.ac.yangge.dao.TopicDao;
import cn.ac.yangge.pojo.ErrorMessage;
import cn.ac.yangge.pojo.TopicCare;
import cn.ac.yangge.pojo.User;
import cn.ac.yangge.pojo.UserConf;
import cn.ac.yangge.pojo.UserPower;
import cn.ac.yangge.service.TopicService;
import cn.ac.yangge.service.UserConfService;
import cn.ac.yangge.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	@Resource
	private UserService userService;
	@Resource
	private UserConfService userConfService;
	@Resource
	private TopicDao topicDao;
	
	@RequestMapping("/getUniteIDByTel")
	@ResponseBody
	public ErrorMessage findUniteIDByTel(@RequestBody User user){
		ErrorMessage err=new ErrorMessage();
		String tel=user.getUserTel();
		user=userService.findUserByTel(tel);
		if(user!=null){
			err.setId(0);
			err.setErr(user.getUniteID());
		}else{
			err.setId(1);
			err.setErr("connt find user");
		}
		return err;
	}
    @RequestMapping("/newUserConf")
    @ResponseBody
    public ErrorMessage newUserInfoFunction(@RequestBody UserConf userConf){
    	int result=userConfService.newUserConf(userConf);
    	ErrorMessage err=new ErrorMessage();
    	if(result>0){
    		err.setId(0);
    		err.setErr(""+result);
    	}else{
    		if(result==-1){
    			err.setId(1);
    			err.setErr("tel canot be null");
    		}else if(result==-2){
    			err.setId(2);
    			err.setErr("tel illegle");
    		}else if(result==-3){
    			err.setId(3);
    			err.setErr("password illegle");
    		}else if(result==-4){
    			err.setId(4);
    			err.setErr("tel has exist");
    		}else{
    			err.setId(5);
    			err.setErr("sql err");
    		}
    	}

    	return err;
    }
    @RequestMapping("/checkCode")
    @ResponseBody
    public ErrorMessage checkConfCode(@RequestBody UserConf userConf){
    	ErrorMessage err=new ErrorMessage();
    	if(userConf.getConfID()>0){
    		UserConf tempConf=userConfService.findUserConfByID(userConf.getConfID());
    		if(tempConf!=null){
    			if(tempConf.getCode()==userConf.getCode()){
    				userService.insertUser(tempConf.getTel(),tempConf.getPassword());
    				err.setId(0);
    				err.setErr("success");
    				userConfService.deleteUserConf(userConf.getConfID());
    				return err;
    			}else{
    				err.setId(1);
    				err.setErr("code err");
    			}
    		}else{
    			err.setId(2);
    			err.setErr("id cannot find");
    		}
    	}else{
			err.setId(2);
			err.setErr("id cannot find");
    	}
    	return err;
    }
    
    @RequestMapping("/missPwd")
    @ResponseBody
    public ErrorMessage findMissingPwd(@RequestBody UserConf userConf){
    	ErrorMessage err=new ErrorMessage();
    	String tel=userConf.getTel();
		User user=userService.findUserByTel(tel);
		if(user!=null){
			userConf.setPassword("");
			int result=userConfService.newMissingUserConf(userConf);
			if(result>0){
				err.setId(0);
				err.setErr(""+result);
			}else if(result!=0){
				err.setId(1);
				err.setErr("tel err");
			}else{
				err.setId(2);
				err.setErr("new userConf faild");
			}
		}else{
			err.setId(3);
			err.setErr("tel not exsit");
		}
		return err;
    }
    @RequestMapping("/checkMissCode")
    @ResponseBody
    public ErrorMessage checkMissCode(@RequestBody UserConf userConf){
    	ErrorMessage err=new ErrorMessage();
    	String password=userConf.getPassword();
    	if(userConf.getConfID()>0&&!(password.length()<3||password.length()>16)){
    		UserConf tempConf=userConfService.findUserConfByID(userConf.getConfID());
    		if(tempConf!=null){
    			if(tempConf.getCode()==userConf.getCode()){
    				userService.updatapwd(tempConf.getTel(),userConf.getPassword());
    				err.setId(0);
    				err.setErr("success");
    				userConfService.deleteUserConf(userConf.getConfID());
    				return err;
    			}else{
    				err.setId(1);
    				err.setErr("code err");
    			}
    		}else{
    			err.setId(2);
    			err.setErr("id cannot find");
    		}
    	}else if(password.length()<3||password.length()>16){
    		err.setId(3);
    		err.setErr("password illegle");
    	}else{
			err.setId(4);
			err.setErr("id cannot find");
    	}
    	return err;
    }
    @RequestMapping("/getPower")
    @ResponseBody
    public UserPower getPower(@RequestBody UserPower userPower){
    	userPower=userService.findUserPower(userPower.getUniteID());
    	if(userPower==null){
    		userPower=new UserPower();
    		userPower.setId(1);
    	}else{
    		userPower.setId(0);
    	}
    	return userPower;
    }
    @RequestMapping("/getTopicCareList")
    @ResponseBody
    public List<Integer> getTopicCareList(@RequestBody User user){
    	String uniteID=user.getUniteID();
    	List<Integer> topicIDList=topicDao.getUserCareList(uniteID);
    	return topicIDList;
    }
    @RequestMapping("/getItemCareList")
    @ResponseBody
    public List<Integer> getItemCareList(@RequestBody User user){
    	String uniteID=user.getUniteID();
    	List<Integer> itemIDList=topicDao.getUserLoveList(uniteID);
    	return itemIDList;
    }
 
}
