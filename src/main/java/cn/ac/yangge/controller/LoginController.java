package cn.ac.yangge.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.ac.yangge.pojo.ErrorMessage;
import cn.ac.yangge.pojo.IdentifierAndUniteID;
import cn.ac.yangge.pojo.User;
import cn.ac.yangge.pojo.UserConf;
import cn.ac.yangge.service.UserService;
import cn.ac.yangge.tool.GetUserSig;
import cn.ac.yangge.tool.MD5Util;

@Controller
@RequestMapping("/login")
public class LoginController {
	@Resource
	private UserService userService;
	@RequestMapping("/pwd")
	@ResponseBody
	public IdentifierAndUniteID pwdLogin(@RequestBody UserConf userConf){
		IdentifierAndUniteID identifierAndUniteID=new IdentifierAndUniteID();
		String passwordMD5=MD5Util.md5Encode16(userConf.getPassword());
		String tel=userConf.getTel();
		User user=userService.findUserByTel(tel);
		if(user!=null){
			if(user.getPassword().equals(passwordMD5)){
				identifierAndUniteID.setId(0);
				identifierAndUniteID.setUniteID(user.getUniteID());
				identifierAndUniteID.setUsersig(GetUserSig.getUserSig(tel));
			}else{
				identifierAndUniteID.setId(1);
				identifierAndUniteID.setUniteID("password err");
			}
		}else{
			identifierAndUniteID.setId(2);
			identifierAndUniteID.setUniteID("tel not exsit");
		}
		return identifierAndUniteID;
	}
}
