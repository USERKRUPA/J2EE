package dao;

import java.sql.SQLException;
import java.util.List;

import pojos.Team;

public interface TeamDao {
	List<String> getTeamAbbrevations() throws SQLException;
	Team getSpecificTeamDetails(String abbrevation) throws SQLException;
	void cleanup() throws SQLException;
}
