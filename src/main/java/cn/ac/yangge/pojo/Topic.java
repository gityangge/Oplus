package cn.ac.yangge.pojo;

public class Topic {
	private int topicID;
	private String topicName="";
	private String topicText="";
	private int topicTextID=0;
	private String topicFrom="";
	private int topicLoveNum;
	private int topicCareNum;
	private long topicTime;
	public int getTopicID() {
		return topicID;
	}
	public void setTopicID(int topicID) {
		this.topicID = topicID;
	}
	public String getTopicName() {
		return topicName;
	}
	public void setTopicName(String topicName) {
		this.topicName = topicName;
	}
	public String getTopicText() {
		return topicText;
	}
	public void setTopicText(String topicText) {
		this.topicText = topicText;
	}
	public int getTopicTextID() {
		return topicTextID;
	}
	public void setTopicTextID(int topicTextID) {
		this.topicTextID = topicTextID;
	}
	public String getTopicFrom() {
		return topicFrom;
	}
	public void setTopicFrom(String topicFrom) {
		this.topicFrom = topicFrom;
	}
	public long getTopicTime() {
		return topicTime;
	}
	public void setTopicTime(long topicTime) {
		this.topicTime = topicTime;
	}
	public int getTopicLoveNum() {
		return topicLoveNum;
	}
	public void setTopicLoveNum(int topicLoveNum) {
		this.topicLoveNum = topicLoveNum;
	}
	public int getTopicCareNum() {
		return topicCareNum;
	}
	public void setTopicCareNum(int topicCareNum) {
		this.topicCareNum = topicCareNum;
	}
}
