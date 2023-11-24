package dao;

import java.io.Serializable;
import java.util.List;

import pojos.Team;

public interface TeamDao {
	//method to insert new team details
	String addTeamDetails(Team nwTeam);
	//method to find out n display team details by it's abbreviation
	List<Team> getAllTeamDetailsByAbbreviation(String abr);
	//method to find out the teams , who need players with max age of the player > specified age
	List<Team> getAllTeamDetailsBySpecifiedAge(int specifiedAge);
	//method to update the team's no of wickets taken n batting avg
	String updateTeamDetails(Serializable id, double battingAvg, int noOfWickets);
}
