package dao;

import java.sql.SQLException;

import pojos.Player;

public interface PlayerDao {
	String insertPlayer(Player player, int teamId) throws SQLException;
	void cleanUp() throws SQLException;
}
