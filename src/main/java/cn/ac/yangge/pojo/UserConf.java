package cn.ac.yangge.pojo;

public class UserConf {
	private int confID;
	private String tel="";
	private String password="";
	private int code;
	private long time;
	public int getConfID() {
		return confID;
	}
	public void setConfID(int confID) {
		this.confID = confID;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String passWord) {
		this.password = passWord;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public long getTime() {
		return time;
	}
	public void setTime(long time) {
		this.time = time;
	}
}
