package cn.ac.yangge.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.ac.yangge.pojo.ErrorMessage;
import cn.ac.yangge.pojo.Topic;
import cn.ac.yangge.pojo.TopicCare;
import cn.ac.yangge.pojo.TopicLove;
import cn.ac.yangge.service.TopicService;

@Controller
@RequestMapping("/topic")
public class TopicController {
	@Resource TopicService topicService;
	
	@RequestMapping("/list")
	@ResponseBody
	public List<Topic> getTopicList(){
		return topicService.getTopicList();
	}
	@RequestMapping("/detail")
	@ResponseBody
	public Topic getTopicDetail(@RequestBody Topic topic){
		return topicService.getTopicDetail(topic.getTopicID());
	}
	@RequestMapping("/new")
	@ResponseBody
	public ErrorMessage newTopic(@RequestBody Topic topic){
		ErrorMessage err=new ErrorMessage();
		int result=topicService.initTopic(topic);
		switch (result) {
		case 0:
			err.setId(1);
			err.setErr("sql error");
			break;
		case -1:
			err.setId(2);
			err.setErr("topic title too long");
			break;
		case -2:
			err.setId(3);
			err.setErr("the uniteID illegle");
			break;
		case -3:
			err.setId(4);
			err.setErr("topic text cannot be empty");
			break;
		case -4:
			err.setId(5);
			err.setErr("other problem happen");
			break;
		default:
			err.setId(0);
			err.setErr(""+result);
			break;
		}
		return err;
	}
	
	@RequestMapping("/update")
	@ResponseBody
	public ErrorMessage updateTopic(@RequestBody Topic topic){
		ErrorMessage err=new ErrorMessage();
		int result=topicService.updateTopic(topic);
		if(result==1){
			err.setId(0);
			err.setErr("success");
		}else{
			err.setId(1);
			err.setErr("sql error");
		}
		return err;
	}
	@RequestMapping("/delete")
	@ResponseBody
	public ErrorMessage deleteTopic(@RequestBody Topic topic){
		ErrorMessage err=new ErrorMessage();
		int result=topicService.deleteTopic(topic.getTopicID());
		switch (result) {
		case 1:
			err.setId(0);
			err.setErr("success");
			break;
		default:
			err.setId(1);
			err.setErr("errors");
			break;
		}
		return err;
	}
	@RequestMapping(value="/love/{addRemove}",method=RequestMethod.POST)
	@ResponseBody
	public ErrorMessage manageTopicLove(@RequestBody TopicLove topicLove,@PathVariable("addRemove") String isAdd){
		ErrorMessage err=new ErrorMessage();
		int result = 0;
		if(isAdd.equals("add")){
			result=topicService.addLove(topicLove);
		}else if(isAdd.equals("remove")){
			result=topicService.removeLove(topicLove);
		}else{
			result=4;
		}
		switch (result) {
		case 0:
			err.setId(1);
			err.setErr("sql error");
			break;
		case 1:
			err.setId(0);
			err.setErr("success");
			break;
		case 2:
			err.setId(2);
			err.setErr("the love action has been done ,uniteID or topic cannot find");
			break;
		case 3:
			err.setId(3);
			err.setErr("uniteID or topic cannot find");
			break;
		case 4:
			err.setId(4);
			err.setErr("add or remove?");
			break;
		default:
			err.setId(5);
			err.setErr("someother problem happen");
			break;
		}
		return err;
	}
	@RequestMapping(value="/care/{addRemove}",method=RequestMethod.POST)
	@ResponseBody
	public ErrorMessage manageTopicCare(@RequestBody TopicCare topicCare,@PathVariable("addRemove") String isAdd){
		ErrorMessage err=new ErrorMessage();
		int result=0;
		if(isAdd.equals("add")){
			result=topicService.addCare(topicCare);
		}else if(isAdd.equals("remove")){
			result=topicService.removeCare(topicCare);
		}else{
			result=4;
		}
		switch (result) {
		case 0:
			err.setId(1);
			err.setErr("sql error");
			break;
		case 1:
			err.setId(0);
			err.setErr("success");
			break;
		case 2:
			err.setId(2);
			err.setErr("the love action has been done or uniteID or topic cannot find");
			break;
		case 4:
			err.setId(4);
			err.setErr("add or remove?");
			break;
		default:
			err.setId(5);
			err.setErr("someother promblem happen");
			break;
		}
		return err;
	}
}
