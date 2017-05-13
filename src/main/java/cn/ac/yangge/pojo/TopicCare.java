package cn.ac.yangge.pojo;

public class TopicCare {
	private int topicCareID;
	private String careUser;
	private int careTopic;
	private long careTime;
	public int getTopicCareID() {
		return topicCareID;
	}
	public void setTopicCareID(int topicCareID) {
		this.topicCareID = topicCareID;
	}
	public String getCareUser() {
		return careUser;
	}
	public void setCareUser(String careUser) {
		this.careUser = careUser;
	}
	public long getCareTime() {
		return careTime;
	}
	public void setCareTime(long careTime) {
		this.careTime = careTime;
	}
	public int getCareTopic() {
		return careTopic;
	}
	public void setCareTopic(int careTopic) {
		this.careTopic = careTopic;
	}
}
