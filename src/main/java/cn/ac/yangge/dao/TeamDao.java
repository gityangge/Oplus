package cn.ac.yangge.dao;

import java.util.List;

import cn.ac.yangge.pojo.Team;

public interface TeamDao {
	public int insertTeam(Team team);
	public Team getTeamByID(int teamID);
	public Team getTeamByTeamName(String teamName);
	public List<Team> getUserTeamList(String teamLeader);
	public int updateTeam(Team team);
	public int deletTeam(int teamID);
}
