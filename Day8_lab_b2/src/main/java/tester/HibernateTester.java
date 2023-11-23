package tester;
import org.hibernate.*;
import static utils.HibernateUtils.*;

public class HibernateTester {
	public static void main(String[] args) {
		try(SessionFactory sf = getSessionFactory()){
			System.out.println("Bootstraping of hibernate done successfully..." + sf);
		}//sf.close() => DBCP cleaned up
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
