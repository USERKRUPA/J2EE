package tester;

import java.util.List;
import java.util.Scanner;
import static utils.HibernateUtils.*;
import org.hibernate.SessionFactory;

import dao.TeamDao;
import dao.TeamDaoImpl;
import pojos.Team;

public class DisplayTeamDetailsByAgeTester {

	public static void main(String[] args) {
		try (Scanner scan = new Scanner(System.in); 
				SessionFactory sf = getSessionFactory()) {
			//Find out the teams , who need players with max age of the player> specified age
			TeamDao teamDao = new TeamDaoImpl();
			System.out.println("Enter age");
			List<Team> teamList = teamDao.getAllTeamDetailsBySpecifiedAge(scan.nextInt());
			System.out.println("Team details required players with max age > specified age:");
			teamList.forEach(System.out::println);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

}
