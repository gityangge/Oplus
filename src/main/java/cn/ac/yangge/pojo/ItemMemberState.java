package cn.ac.yangge.pojo;

public class ItemMemberState {
	private int memberStateID;
	private int memberNeedAndroid=-1;
	private int memberNeediOS=-1;
	private int memberNeedBack=-1;
	private int memberNeedWeb=-1;
	private int memberNeedManager=-1;
	private String memberNeedOther="";

	public void initMemberState(){
		memberNeedAndroid=0;
		memberNeediOS=0;
		memberNeedBack=0;
		memberNeedWeb=0;
		memberNeedManager=0;
	}
	public int getMemberStateID() {
		return memberStateID;
	}
	public void setMemberStateID(int memberStateID) {
		this.memberStateID = memberStateID;
	}
	public int getMemberNeedAndroid() {
		return memberNeedAndroid;
	}
	public void setMemberNeedAndroid(int memberNeedAndroid) {
		this.memberNeedAndroid = memberNeedAndroid;
	}
	public int getMemberNeediOS() {
		return memberNeediOS;
	}
	public void setMemberNeediOS(int memberNeediOS) {
		this.memberNeediOS = memberNeediOS;
	}
	public int getMemberNeedBack() {
		return memberNeedBack;
	}
	public void setMemberNeedBack(int memberNeedBack) {
		this.memberNeedBack = memberNeedBack;
	}
	public int getMemberNeedWeb() {
		return memberNeedWeb;
	}
	public void setMemberNeedWeb(int memberNeedWeb) {
		this.memberNeedWeb = memberNeedWeb;
	}
	public int getMemberNeedManager() {
		return memberNeedManager;
	}
	public void setMemberNeedManager(int memberNeedManager) {
		this.memberNeedManager = memberNeedManager;
	}
	public String getMemberNeedOther() {
		return memberNeedOther;
	}
	public void setMemberNeedOther(String memberNeedOther) {
		this.memberNeedOther = memberNeedOther;
	}

}
