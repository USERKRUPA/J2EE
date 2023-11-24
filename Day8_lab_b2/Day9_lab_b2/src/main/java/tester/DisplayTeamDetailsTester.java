package tester;

import java.util.List;
import java.util.Scanner;
import org.hibernate.SessionFactory;

import dao.TeamDao;
import dao.TeamDaoImpl;
import pojos.Team;

import static utils.HibernateUtils.*;


public class DisplayTeamDetailsTester {

	public static void main(String[] args) {
		try(Scanner scan = new Scanner(System.in);
			SessionFactory sf = getSessionFactory()){
			System.out.println("Hibernate is bootstraping done...");
			//Find out n display team details by it's abbreviation
			TeamDao teamDao = new TeamDaoImpl();
			System.out.println("Enter abbreviation: ");
			List<Team> teamList = teamDao.getAllTeamDetailsByAbbreviation(scan.next());
			System.out.println("All Team Details");
			teamList.forEach(System.out::println);
		}//sf.close() => DBCP cleaned up
		catch (Exception e) {
			e.printStackTrace();
		}

	}

}
