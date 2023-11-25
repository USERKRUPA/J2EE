package dao;

import static utils.HibernateUtils.getFactory;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import pojos.Team;

public class TeamDaoImpl implements TeamDao {

	@Override
	public List<String> getTeamAbbrevations() {
		
		return null;
	}

	@Override
	public Team getSpecificTeamDetails(String abbrevation) {
		
		return null;
	}

	@Override
	public String addNewTeam(Team team) {
		String msg = "Team added failed";
		//get the session
		Session session = getFactory().getCurrentSession();
		//begin a transaction
		Transaction transaction = session.beginTransaction();
	
		try {
			
			
			
			transaction.commit();
			msg = "Team added";
		}catch(RuntimeException e) {
			if(transaction != null)
				transaction.rollback();
			throw e;
		}
		return msg;
	}

}
