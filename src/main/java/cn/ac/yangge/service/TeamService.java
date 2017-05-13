package cn.ac.yangge.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.ac.yangge.dao.TeamDao;
import cn.ac.yangge.pojo.ArraryHelp;
import cn.ac.yangge.pojo.Team;
import cn.ac.yangge.tool.ObjectAndJson;

@Service("TeamService")
public class TeamService {
	@Resource
	TeamDao teamDao;
	@Resource
	UserService userService;
	public int newTeam(Team team){
		if(team.getTeamName().isEmpty()){
			return -1;//队伍名不能为空
		}else if(!teamNameisOK(team.getTeamName())){
			return -4;//队伍名已存在
		}else if(team.getTeamLeader().isEmpty()){
			return -2;//发起者不能为空
		}else if(userService.findUserByUniteID(team.getTeamLeader())==null){
			return -3;//发起者不存在
		}else{
			if(!team.getTeamMemberList().isEmpty()){
				ArraryHelp arraryHelp=new ArraryHelp();
				List<String> memberList=new ArrayList<String>();
				memberList.addAll(team.getTeamMemberList());
				arraryHelp.setList(memberList);
				team.setTeamMember(ObjectAndJson.ObjectToJson(arraryHelp));
				team.setTeamMemberList(null);
			}
			team.setTeamTime(System.currentTimeMillis());
			if(teamDao.insertTeam(team)==1){
				return team.getTeamID();
			}else{
				return 0;
			}
		}
	}
	public int addMemberToTeam(List<String> newMemberList,int teamID){
		List<String> addMemberList=new ArrayList<String>();
		for(int i=0;i<newMemberList.size();i++){
			if(userService.findUserByUniteID(newMemberList.get(i))!=null){
				addMemberList.add(newMemberList.get(i));
			}
		}
		/** 去重 **/
		HashSet h = new HashSet<String>(addMemberList);
		addMemberList.clear();
		addMemberList.addAll(h);
		
		if(addMemberList.size()!=0){
			Team oldTeam=teamDao.getTeamByID(teamID);
			if(oldTeam==null){
				return 3;//无效teamID
			}
			ArraryHelp oldList;	
			if(oldTeam.getTeamMember().isEmpty()){
				oldList=new ArraryHelp();
				oldList.initList();
			}else{
				oldList=ObjectAndJson.JsonToObject(oldTeam.getTeamMember());
			}
			newMemberList.clear();
			newMemberList.addAll(addMemberList);
			newMemberList.addAll(oldList.getList());
			h=new HashSet<String>(newMemberList);
			newMemberList.clear();
			newMemberList.addAll(h);
			oldList.setList(newMemberList);
			String newMemberString=ObjectAndJson.ObjectToJson(oldList);
			oldTeam.setTeamMember(newMemberString);
			return teamDao.updateTeam(oldTeam);
		}else{
			return 2;//无效成员列表
		}
		
	}
	public int removeMemberFromList(List<String> removeMemberList,int teamID){
		if(removeMemberList.isEmpty()){
			return -1;//无需要删除的成员
		}else{
			Team team=findTeamByID(teamID);
			if(team==null){
				return -2;//无效teamid
			}else{
				String memberString=team.getTeamMember();
				if(memberString.isEmpty()){
					return -3;//team中还没有成员
				}else{
					ArraryHelp removeArrary=ObjectAndJson.JsonToObject(memberString);
					int oldSize=removeArrary.getList().size();
					List<String> resultMemberList=new ArrayList<String>();
					for(int i=0,isRemove=0;i<removeArrary.getList().size();i++){
						isRemove=0;
						for(int j=0;j<removeMemberList.size();j++){
							if(removeArrary.getList().get(i).equals(removeMemberList.get(j))){
								isRemove=1;
								break;
							}
						}
						if(isRemove==0){
							resultMemberList.add(removeArrary.getList().get(i));
						}
					}
					if(resultMemberList.size()==oldSize){
						return -4;
					}else{
						removeArrary.setList(resultMemberList);
						team.setTeamMember(ObjectAndJson.ObjectToJson(removeArrary));
						team.setTeamMemberList(null);
						return teamDao.updateTeam(team);
					}
				}
			}
			
		}
	}
	public List<Team> getTeamList(String uniteID){
		return teamDao.getUserTeamList(uniteID);
	}
	public ArraryHelp getTeamMemberList(int teamID){
		Team team=teamDao.getTeamByID(teamID);
		ArraryHelp memberList=ObjectAndJson.JsonToObject(team.getTeamMember());
		return memberList;
	}
	public Team findTeamByID(int teamID){
		return teamDao.getTeamByID(teamID);	
	}
	public boolean teamNameisOK(String teamName){
		if(teamDao.getTeamByTeamName(teamName)==null){
			return true;
		}else{
			return false;
		}
	}
}
