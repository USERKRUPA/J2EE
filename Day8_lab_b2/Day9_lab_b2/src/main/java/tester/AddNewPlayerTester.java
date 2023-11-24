package tester;
import static utils.HibernateUtils.getSessionFactory;

import java.time.LocalDate;
import java.util.Scanner;

import org.hibernate.SessionFactory;

import dao.PlayerDao;
import dao.PlayerDaoImpl;
import pojos.Player;

public class AddNewPlayerTester {
	public static void main(String[] args) {
		try(Scanner scan = new Scanner(System.in);SessionFactory sf = getSessionFactory()){
			System.out.println("Bootstraping of hibernate done successfully..." + sf);
			//create dao instance
			PlayerDao playerDao = new PlayerDaoImpl();
			System.out.println("Enter new Player details String name, String last name, \r\n"
					+ "			String DOB yy-mm-dd, double batting average, int wicket taken");
			//int player_id, String first_name, String last_name, Date dob, double batting_avg, int wickets_taken,
			//int team_id
			Player player = new Player(scan.next(), scan.next(), LocalDate.parse(scan.next()), scan.nextDouble(), scan.nextInt());
			System.out.println(playerDao.addPlayerDetails(player));
		}//sf.close() => DBCP cleaned up
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
