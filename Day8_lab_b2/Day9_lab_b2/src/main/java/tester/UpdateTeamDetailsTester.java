package tester;

import java.util.Scanner;
import org.hibernate.SessionFactory;

import dao.TeamDao;
import dao.TeamDaoImpl;

import static utils.HibernateUtils.*;

public class UpdateTeamDetailsTester {

	public static void main(String[] args) {
		try(Scanner scan = new Scanner(System.in); 
				SessionFactory sf = getSessionFactory()){
			TeamDao teamDao = new TeamDaoImpl();
			System.out.println("Enter team id, batting average and no of wickets taken: ");
			System.out.println(teamDao.updateTeamDetails(scan.nextInt(), scan.nextDouble(), scan.nextInt()));
		}
		catch(Exception e) {
			e.printStackTrace();
		}

	}

}
