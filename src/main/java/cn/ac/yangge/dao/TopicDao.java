package cn.ac.yangge.dao;

import java.util.List;

import cn.ac.yangge.pojo.Topic;
import cn.ac.yangge.pojo.TopicCare;
import cn.ac.yangge.pojo.TopicLove;

public interface TopicDao {
	/*** love and care***/
	public int addLove(TopicLove topicLove);
	public int removeLove(TopicLove topicLove);
	public int addCare(TopicCare topicCare);
	public int removeCare(TopicCare topicCare);
	public int getCountLoveToTopic(TopicLove topicLove);
	public int getCountCareToTopic(TopicCare topicCare);
	public List<Integer> getUserLoveList(String loveUser);
	public List<Integer> getUserCareList(String careUser);
	public int changeLoveNum(Topic topic);
	public int changeCareNum(Topic topic);
	
	/** init topic **/
	public int insertTopic(Topic topic);
	public int initTopicText(Topic topic);
	/*get information*/
	public Topic getTopicByID(int topicID);
	public Topic getTopicByTopicName(String TopicName);
	public List<Topic> getTopicList();
	public String getTopicText(int topicTextID);
	/** change topic state **/
	public int updateTopic(Topic topic);
	public int deleteTopic(int topicID);
	public int updateTopicText(Topic topic);
	/*  */
}
