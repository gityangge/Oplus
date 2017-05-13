package cn.ac.yangge.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.ac.yangge.pojo.ErrorMessage;
import cn.ac.yangge.pojo.ItemCareDetail;
import cn.ac.yangge.pojo.ItemLoveDetail;
import cn.ac.yangge.service.LoveCareService;

@Controller
@RequestMapping("/action")
public class ActionController {
	@Resource
	LoveCareService loveCareService;
	
	@RequestMapping("/addLove")
	@ResponseBody
	public ErrorMessage addLove(@RequestBody ItemLoveDetail itemLoveDetail){
		ErrorMessage err=new ErrorMessage();
		int result=loveCareService.addLove(itemLoveDetail);
		switch (result) {
		case 0:
			err.setId(1);
			err.setErr("love detail regist failed");
			break;
		case 1:
			err.setId(0);
			err.setErr("success");
			break;
		case 2:
			err.setId(2);
			err.setErr("uniteid ERR");
			break;
		case 3:
			err.setId(3);
			err.setErr("item id ERR");
			break;
		case 4:
			err.setId(4);
			err.setErr("update failed");
			break;
		case 5:
			err.setId(5);
			err.setErr("love action has achieved");
			break;
		default:
			err.setId(6);
			err.setErr("unknow problem cause");
			break;
		}
		return err;
	}
	
	@RequestMapping("/removeLove")
	@ResponseBody
	public ErrorMessage removeLove(@RequestBody ItemLoveDetail itemLoveDetail){
		ErrorMessage err=new ErrorMessage();
		int result=loveCareService.removeLove(itemLoveDetail);
		switch (result) {
		case 0:
			err.setId(1);
			err.setErr("delete record failed");
			break;
		case 1:
			err.setId(0);
			err.setErr("success");
			break;
		case 2:
			err.setId(2);
			err.setErr("unite id ERR");
			break;
		case 3:
			err.setId(3);
			err.setErr("item id ERR");
			break;
		case 4:
			err.setId(4);
			err.setErr("update detail data failed");
			break;
		default:
			err.setId(5);
			err.setErr("unknow problem cause");
			break;
		}
		return err;
	}
	
	@RequestMapping("/addCare")
	@ResponseBody
	public ErrorMessage addCare(@RequestBody ItemCareDetail itemCareDetail){
		ErrorMessage err=new ErrorMessage();
		int result=loveCareService.addCare(itemCareDetail);
		switch (result) {
		case 0:
			err.setId(1);
			err.setErr("care detail regist failed");
			break;
		case 1:
			err.setId(0);
			err.setErr("success");
			break;
		case 2:
			err.setId(2);
			err.setErr("uniteid ERR");
			break;
		case 3:
			err.setId(3);
			err.setErr("item id ERR");
			break;
		case 4:
			err.setId(4);
			err.setErr("update failed");
			break;
		case 5:
			err.setId(5);
			err.setErr("love action has achieved");
			break;
		default:
			err.setId(6);
			err.setErr("unknow problem cause");
			break;
		}
		return err;
	}
	
	@RequestMapping("/removeCare")
	@ResponseBody
	public ErrorMessage removeCare(@RequestBody ItemCareDetail itemCareDetail){
		ErrorMessage err=new ErrorMessage();
		int result=loveCareService.removeCare(itemCareDetail);
		switch (result) {
		case 0:
			err.setId(1);
			err.setErr("delete record failed");
			break;
		case 1:
			err.setId(0);
			err.setErr("success");
			break;
		case 2:
			err.setId(2);
			err.setErr("unite id ERR");
			break;
		case 3:
			err.setId(3);
			err.setErr("item id ERR");
			break;
		case 4:
			err.setId(4);
			err.setErr("update detail data failed");
			break;
		default:
			err.setId(5);
			err.setErr("unknow problem cause");
			break;
		}
		return err;
	}
}
