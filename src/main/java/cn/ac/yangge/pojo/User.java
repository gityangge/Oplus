package cn.ac.yangge.pojo;

public class User {
	private int userID=0;
	private String uniteID="";
	private String password="";
	private String userTel="";
	private String userName="";
	private int userType=1;
	private long userTime=0;
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUserTel() {
		return userTel;
	}
	public void setUserTel(String userTel) {
		this.userTel = userTel;
	}
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public String getUniteID() {
		return uniteID;
	}
	public void setUniteID(String uniteID) {
		this.uniteID = uniteID;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getUserType() {
		return userType;
	}
	public void setUserType(int userType) {
		this.userType = userType;
	}
	public long getUserTime() {
		return userTime;
	}
	public void setUserTime(long userTime) {
		this.userTime = userTime;
	}
}
