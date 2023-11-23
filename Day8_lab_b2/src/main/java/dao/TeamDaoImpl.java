package dao;

import pojos.Team;
import org.hibernate.*;
import static utils.HibernateUtils.getSessionFactory;


public class TeamDaoImpl implements TeamDao {
	@Override
	public String addTeamDetails(Team nwTeam) {
		//1. getting the hibernate session
		Session session = getSessionFactory().openSession() ;
		//2. begin a transaction
		Transaction tx = session.beginTransaction();
		try{
			session.save(nwTeam);
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
		
		return "New team is added with id " + nwTeam.getTeamId();
	}

}
