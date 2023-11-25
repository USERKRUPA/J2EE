package dao;

import java.util.List;

import pojos.Team;

public interface TeamDao {
	String addNewTeam(Team team);
	List<String> getTeamAbbrevations();
	Team getSpecificTeamDetails(String abbrevation);
}
