package dao;

import static utils.HibernateUtils.getSessionFactory;

import org.hibernate.Session;
import org.hibernate.Transaction;

import pojos.Player;

public class PlayerDaoImpl implements PlayerDao {
	

	@Override
	public String addPlayerDetails(Player nwPlayer){
		//int player_id, String first_name, String last_name, Date dob, double batting_avg, int wickets_taken,
		//int team_id
	
	
	Session session = getSessionFactory().openSession() ;
	//2. begin a transaction
	Transaction tx = session.beginTransaction();
	try{
		session.save(nwPlayer);
		//end of try => success
		tx.commit();
	}catch (RuntimeException e) {
		//rollback the transaction and rethrow exception to caller
		if(tx != null)
			tx.rollback();
		throw e;
	}finally {
		if(session != null)
			session.close();
	}
	
	return "New Player is added with id " + nwPlayer.getPlayer_id();
}
	

}
