package cn.ac.yangge.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.ac.yangge.dao.TopicDao;
import cn.ac.yangge.pojo.Topic;
import cn.ac.yangge.pojo.TopicCare;
import cn.ac.yangge.pojo.TopicLove;

@Service("TopicService")
public class TopicService {
	@Resource TopicDao topicDao;
	@Resource UserService userService;
	public int initTopic(Topic topic){
		/* init primary property */
		if(topic.getTopicName().length()>30){
			return -1;
		}else if(topic.getTopicFrom().isEmpty()||userService.findUserByUniteID(topic.getTopicFrom())==null){
			return -2;//uniteID
		}else{
			topic.setTopicCareNum(0);
			topic.setTopicLoveNum(0);
		}
		/*init text*/
		if(topic.getTopicText().length()>0){
			topicDao.initTopicText(topic);
			if(topic.getTopicTextID()==0){
				return 0;//sql error
			}else{
				String content=topic.getTopicText();
				if(content.length()>75){
					topic.setTopicText(content.substring(0, 75));
				}
			}
		}else{
			return -3;//topic text too short
		}
		/* init end and set time */
		topic.setTopicTime(System.currentTimeMillis());
		
		if(topicDao.insertTopic(topic)!=0){
			return topic.getTopicID();
		}else{
			return 0;//sql error
		}
	}
	public int updateTopic(Topic topic){
		Topic topicTemp=topicDao.getTopicByID(topic.getTopicID());
		/*change infprmation*/
		if(topic.getTopicName().isEmpty()){
			topic.setTopicName(topicTemp.getTopicName());
		}
		if(topic.getTopicText().isEmpty()){
			topic.setTopicText(topicTemp.getTopicText());
		}else{
			topic.setTopicTextID(topicTemp.getTopicTextID());
			topicDao.updateTopicText(topic);
			if(topic.getTopicText().length()>75){
				topic.setTopicText(topic.getTopicText().substring(0,75));
			}
		}
		topic.setTopicCareNum(topicTemp.getTopicCareNum());
		topic.setTopicLoveNum(topicTemp.getTopicLoveNum());
		topic.setTopicFrom(topicTemp.getTopicFrom());
		topic.setTopicTime(topicTemp.getTopicTime());
		return topicDao.updateTopic(topic);
	}
	public int deleteTopic(int topicID){
		return topicDao.deleteTopic(topicID);
	}
	public int addLove(TopicLove topicLove){
		if(!checkUserAndTopicID(topicLove.getLoveUser(), topicLove.getLoveTopic())){
			return 3;
		}
		if(topicDao.getCountLoveToTopic(topicLove)==0){
			Topic topic=new Topic();
			topic.setTopicID(topicLove.getLoveTopic());
			topic.setTopicLoveNum(1);
			if(topicDao.changeLoveNum(topic)==1){
				topicLove.setLoveTime(System.currentTimeMillis());
				return topicDao.addLove(topicLove);				
			}else{
				return 0;
			}
		}else{
			return 2;
		}
		
	}
	public int removeLove(TopicLove topicLove){
		if(topicDao.getCountLoveToTopic(topicLove)==0){
			return 2;
		}else{
			Topic topic=new Topic();
			topic.setTopicID(topicLove.getLoveTopic());
			topic.setTopicLoveNum(-1);
			if(topicDao.changeLoveNum(topic)==1){
				return topicDao.removeLove(topicLove);							
			}else{
				return 0;
			}
		}
	}
	public int addCare(TopicCare topicCare){
		if(!checkUserAndTopicID(topicCare.getCareUser(), topicCare.getCareTopic())){
			return 3;
		}
		if(topicDao.getCountCareToTopic(topicCare)==0){
			Topic topic=new Topic();
			topic.setTopicID(topicCare.getCareTopic());
			topic.setTopicCareNum(1);
			if(topicDao.changeCareNum(topic)==1){
				topicCare.setCareTime(System.currentTimeMillis());
				return topicDao.addCare(topicCare);							
			}else{
				System.out.println("sss");
				return 0; 
			}
		}else{
			
			return 2;
		}
	}
	public int removeCare(TopicCare topicCare){
		if(topicDao.getCountCareToTopic(topicCare)==0){
			return 2;
		}else{
			Topic topic=new Topic();
			topic.setTopicID(topicCare.getCareTopic());
			topic.setTopicCareNum(-1);
			if(topicDao.changeCareNum(topic)==1){
				return topicDao.removeCare(topicCare);				
			}else{
				return 0;
			}
		}
	}
	public List<Topic> getTopicList(){
		return topicDao.getTopicList();
	}
	public Topic getTopicDetail(int topicID){
		Topic topic=topicDao.getTopicByID(topicID);
		if(topic==null){
			return null;
		}
		String text=topicDao.getTopicText(topic.getTopicTextID());
		if(!text.isEmpty()){
			topic.setTopicText(text);
			return topic;
		}else{
			return null;
		}
	}
	public List<Integer> getCareList(String uniteID){
		return topicDao.getUserCareList(uniteID);
	}
	private boolean checkUserAndTopicID(String uniteID,int topicID){
		if(uniteID==null||uniteID.isEmpty()||userService.findUserByUniteID(uniteID)==null){
			return false;
		}
		if(topicID<=0||topicDao.getTopicByID(topicID)==null){
			return false;
		}
		return true;
	}
	
}
