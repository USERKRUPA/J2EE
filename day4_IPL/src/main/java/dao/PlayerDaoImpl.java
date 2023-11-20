package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import pojos.Player;

import static utils.DBUtils.*;

public class PlayerDaoImpl implements PlayerDao {
	private Connection cn;
	private PreparedStatement pstInsert;

	public PlayerDaoImpl() throws SQLException {
		cn = getConnection();
		pstInsert = cn.prepareStatement("insert into players values(default, ?,?,?,?,?,?)");
		System.out.println("Player dao created...");
	}

	@Override
	public String insertPlayer(Player player, int teamId) throws SQLException{
		//int player_id, String first_name, String last_name, Date dob, double batting_avg, int wickets_taken,
		//int team_id
		pstInsert.setString(1, player.getFirst_name());
		pstInsert.setString(2,player.getLast_name());
		pstInsert.setDate(3, player.getDob());
		pstInsert.setDouble(4, player.getBatting_avg());
		pstInsert.setInt(5, player.getWickets_taken());
		pstInsert.setInt(6, teamId);
		
		int rowsAffected = pstInsert.executeUpdate();
		if(rowsAffected == 1)
			return "Player added";
		return "Player not added";
	}
	
	public void cleanUp() throws SQLException{
		if(pstInsert != null)
			pstInsert.close();
		closeConnection();
	}

}
