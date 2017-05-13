package cn.ac.yangge.pojo;

public class TopicLove {
	private int topicLoveID;
	private String loveUser;
	private int loveTopic;
	private long loveTime;
	public int getTopicLoveID() {
		return topicLoveID;
	}
	public void setTopicLoveID(int topicLoveID) {
		this.topicLoveID = topicLoveID;
	}
	public String getLoveUser() {
		return loveUser;
	}
	public void setLoveUser(String loveUser) {
		this.loveUser = loveUser;
	}
	public long getLoveTime() {
		return loveTime;
	}
	public void setLoveTime(long loveTime) {
		this.loveTime = loveTime;
	}
	public int getLoveTopic() {
		return loveTopic;
	}
	public void setLoveTopic(int loveTopic) {
		this.loveTopic = loveTopic;
	}
}
