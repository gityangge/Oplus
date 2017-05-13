package cn.ac.yangge.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.ac.yangge.pojo.ErrorMessage;
import cn.ac.yangge.pojo.Team;
import cn.ac.yangge.service.TeamService;

@Controller
@RequestMapping("/team")
public class TeamController {
	@Resource
	TeamService teamService;
	@RequestMapping("/list")
	@ResponseBody
	public List<Team> getTeamList(@RequestBody Team team){
		return teamService.getTeamList(team.getTeamLeader());
	}
	@RequestMapping("/newTeam")
	@ResponseBody
	public ErrorMessage newTeam(@RequestBody Team team){
		ErrorMessage err=new ErrorMessage();
		int result=teamService.newTeam(team);
		switch (result) {
		case -1:
			err.setId(1);
			err.setErr("teamName cannot be null");
			break;
		case -2:
			err.setId(2);
			err.setErr("Leader cannot be null");
			break;
		case -3:
			err.setId(3);
			err.setErr("Leader not exsit");
			break;
		case -4:
			err.setId(4);
			err.setErr("teamName has exsit");
			break;
		case 0:
			err.setId(4);
			err.setErr("sql err");
			break;
		default:
			err.setId(0);
			err.setErr(result+"");
			break;
		}
		return err;
	}
	@RequestMapping("/addMember")
	@ResponseBody
	public ErrorMessage addMemberToTeam(@RequestBody Team team){
		ErrorMessage err=new ErrorMessage();
		int result=teamService.addMemberToTeam(team.getTeamMemberList(), team.getTeamID());
		switch (result) {
		case 0:
			err.setId(1);
			err.setErr("sql err");
			break;
		case 1:
			err.setId(0);
			err.setErr("success");
			break;
		case 2:
			err.setId(2);
			err.setErr("member list illegle");
			break;
		case 3:
			err.setId(3);
			err.setErr("team id err");
			break;
		default:
			err.setId(4);
			err.setErr("some unkwon problem happen");
			break;
		}
		return err;
	}
	@RequestMapping("/removeMember")
	@ResponseBody
	public ErrorMessage removeMemberFromTeam(@RequestBody Team team){
		ErrorMessage err=new ErrorMessage();
		int result=teamService.removeMemberFromList(team.getTeamMemberList(), team.getTeamID());
		switch (result) {
		case 1:
			err.setId(0);
			err.setErr("success");
			break;
		case -1:
			err.setId(1);
			err.setErr("member list is null");
			break;
		case -2:
			err.setId(2);
			err.setErr("cannot find the team");
			break;
		case -3:
			err.setId(3);
			err.setErr("the team never have member,cannot remove");
			break;
		case -4:
			err.setId(4);
			err.setErr("post memberlist not have effective member");
			break;
		case 0:
			err.setId(5);
			err.setErr("sql err");
			break;
		default:
			err.setId(6);
			err.setErr("unkwon problem happen");
			break;
		}
		return err;
	}
}
