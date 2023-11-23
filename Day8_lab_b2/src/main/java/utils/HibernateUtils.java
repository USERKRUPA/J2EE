package utils;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;

//create singleton, immutable, inherently thread-safe, heavy weight(time consuming SF) 
public class HibernateUtils {
	private static SessionFactory sessionFactory;
	static {
		System.out.println("In static block... configuratoin done");
		sessionFactory = new Configuration().configure().buildSessionFactory();	
	}
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
}
