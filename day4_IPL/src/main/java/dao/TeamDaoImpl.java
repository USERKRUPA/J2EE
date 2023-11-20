package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import pojos.Team;

import static utils.DBUtils.*;

public class TeamDaoImpl implements TeamDao {
	private Connection cn;
	private PreparedStatement pstSelect;
	private PreparedStatement pstSelectAbbr;
	
	public TeamDaoImpl() throws SQLException {
		this.cn = getConnection();
		this.pstSelect = cn.prepareStatement("select * from teams where abbrevation = ?");
		this.pstSelectAbbr = cn.prepareStatement("select abbrevation from teams");
		System.out.println("Team dao created...");
	}

	@Override
	public Team getSpecificTeamDetails(String abbrevation) throws SQLException {
		pstSelect.setString(1, abbrevation);
		try(ResultSet rst = pstSelect.executeQuery()){
			if(rst.next()) {
				//int team_id, String name, String abbrevation, String owner, int max_age, 
				//double batting_avg, int wickets_taken
				return new Team(rst.getInt(1), rst.getString(2), rst.getString(3), rst.getString(4), rst.getInt(5), rst.getDouble(6), rst.getInt(7));
			}
		}
		return null;
	}
	
	@Override
	public List<String> getTeamAbbrevations() throws SQLException {
		List<String> abbrevations = new ArrayList<>();
		try(ResultSet rst = pstSelectAbbr.executeQuery()){
			while(rst.next()) {
				if(!abbrevations.contains(rst.getString(1)))
					abbrevations.add(rst.getString(1));
			}
		}
		return abbrevations;
	}
	
	public void cleanup() throws SQLException {
		if(pstSelect != null)
			pstSelect.close();
		closeConnection();
	}
	
}
