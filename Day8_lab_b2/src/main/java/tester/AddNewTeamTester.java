package tester;
import org.hibernate.*;

import dao.TeamDao;
import dao.TeamDaoImpl;
import pojos.Team;

import static utils.HibernateUtils.*;

import java.util.Scanner;

public class AddNewTeamTester {
	public static void main(String[] args) {
		try(Scanner scan = new Scanner(System.in);SessionFactory sf = getSessionFactory()){
			System.out.println("Bootstraping of hibernate done successfully..." + sf);
			//create dao instance
			TeamDao teamDao = new TeamDaoImpl();
			System.out.println("Enter new team details String name, String abbreviation, \r\n"
					+ "			String owner, int maxAge, double minBattingAvg,\r\n"
					+ "			int minWicketsTaken");
			//
			Team team = new Team(scan.next(), scan.next(), scan.next(), scan.nextInt(), scan.nextDouble(), scan.nextInt());
			System.out.println(teamDao.addTeamDetails(team));
		}//sf.close() => DBCP cleaned up
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
