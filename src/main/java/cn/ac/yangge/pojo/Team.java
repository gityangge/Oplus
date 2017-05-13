package cn.ac.yangge.pojo;

import java.util.ArrayList;
import java.util.List;

public class Team {
	private int teamID;
	private String teamName="";
	private String teamLeader="";
	private String teamMember="";
	private List<String> teamMemberList=new ArrayList<String>();
	private long teamTime;
	public int getTeamID() {
		return teamID;
	}
	public void setTeamID(int teamID) {
		this.teamID = teamID;
	}
	public String getTeamName() {
		return teamName;
	}
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	public String getTeamLeader() {
		return teamLeader;
	}
	public void setTeamLeader(String teamLeader) {
		this.teamLeader = teamLeader;
	}
	public String getTeamMember() {
		return teamMember;
	}
	public void setTeamMember(String teamMember) {
		this.teamMember = teamMember;
	}
	public List<String> getTeamMemberList() {
		return teamMemberList;
	}
	public void setTeamMemberList(List<String> teamMemberList) {
		this.teamMemberList = teamMemberList;
	}
	public long getTeamTime() {
		return teamTime;
	}
	public void setTeamTime(long teamTime) {
		this.teamTime = teamTime;
	}
}
