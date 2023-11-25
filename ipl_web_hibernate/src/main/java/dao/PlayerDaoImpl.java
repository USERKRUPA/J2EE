package dao;

import pojos.Player;
import pojos.Team;

import static utils.HibernateUtils.getFactory;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class PlayerDaoImpl implements PlayerDao {

	@Override
	public String addNewPlayer(Player player, int teamId) {
		String msg = "Player added failed";
		//get the session
		Session session = getFactory().getCurrentSession();
		//begin a transaction
		Transaction transaction = session.beginTransaction();
	
		try {
			//get team reference to add player to team
			Team team = session.get(Team.class, teamId);
			//call addPlayer method of Team class
			team.addPlayer(player);
			transaction.commit();
			msg = "Player added";
		}catch(RuntimeException e) {
			if(transaction != null)
				transaction.rollback();
			throw e;
		}
		return msg;
	}

}
