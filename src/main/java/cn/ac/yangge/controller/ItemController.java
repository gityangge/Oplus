package cn.ac.yangge.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.ac.yangge.pojo.ErrorMessage;
import cn.ac.yangge.pojo.Item;
import cn.ac.yangge.pojo.ItemContent;
import cn.ac.yangge.pojo.PasswordConf;
import cn.ac.yangge.service.ItemService;

@Controller
@RequestMapping("/item")
public class ItemController {
	@Resource
	private ItemService itemService;
	
	@RequestMapping("/newItem")
	@ResponseBody
	public ErrorMessage newItem(@RequestBody Item item){
		ErrorMessage err=new ErrorMessage();
		int result=itemService.insertItem(item);
		switch (result) {
		case 0:
			err.setId(7);
			err.setErr("mysql problem cause");
			break;
		case 1:
			err.setId(0);
			err.setErr("success");
			break;
		case 2:
			err.setId(2);
			err.setErr("itemName cannot be empty");			
			break;
		case 3:
			err.setId(3);
			err.setErr("itemLeader cannot be empty");
			break;
		case 4:
			err.setId(4);
			err.setErr("itemName has exsit");
			break;
		case 5:
			err.setId(5);
			err.setErr("team id err");
			break;
		case 6:
			err.setId(6);
			err.setErr("teamLeader connot find");
			break;
		case 7:
			err.setId(7);
			err.setErr("sql err");
			break;
		case 8:
			err.setId(8);
			err.setErr("memberState list cannot find");
			break;
		
		case 9:
			err.setId(9);
			err.setErr("the Type must be true");
		break;	
		default:
			err.setId(10);
			err.setErr("some unknow problem cause");
			break;
		}
		return err;
	}
	
	@RequestMapping("/itemList")
	@ResponseBody
	public List<Item> getItemList(@RequestBody PasswordConf pwd){
		if(pwd.getPassword().equals("lll")){
			List<Item> itemList=itemService.getItemList();
			return itemList;
		}else{
			return null;
		}
		
	}
	@RequestMapping("/detailContent")
	@ResponseBody
	public ItemContent getDetailContent(@RequestBody ItemContent itemContent){
		return itemService.getDetailContent(itemContent.getItemContentID());
	}
}
