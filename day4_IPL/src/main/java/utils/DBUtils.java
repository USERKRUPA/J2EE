package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtils {
	private static Connection cn;
	public static Connection openConnection() throws SQLException {
		//jdbc:mysql://localhost:3306/advjava?useSSL=false&allowPublicKeyRetrieval=true", "root", "mysql"
		cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/advjava?useSSL=false&allowPublicKeyRetrieval=true", "root", "mysql");
		return cn;
	}
	
	public static void closeConnection() throws SQLException{
		if(cn != null)
			cn.close();
	}
	
	public static Connection getConnection(){
		return cn;
	}
}
