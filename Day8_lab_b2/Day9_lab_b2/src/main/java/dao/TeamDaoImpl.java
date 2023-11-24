package dao;

import pojos.Team;
import org.hibernate.*;
import static utils.HibernateUtils.getSessionFactory;

import java.io.Serializable;
import java.util.List;


public class TeamDaoImpl implements TeamDao {
	@Override
	public String addTeamDetails(Team nwTeam) {
		//1. getting the hibernate session
		Session session = getSessionFactory().getCurrentSession() ;
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

	//Method to find out n display team details by it's abbreviation
	@Override
	public List<Team> getAllTeamDetailsByAbbreviation(String abr) {
		List<Team> list = null;
		//get session from session factory
		Session session = getSessionFactory().getCurrentSession();
		// begin a transaction
		Transaction transaction = session.beginTransaction();
		try {
			String jpqlQuery = "select t from Team t where abbreviation = :abbr"; 
			//createQuery returns Query<Team> through so we can call getResultList() of Query interface=> mtd chaining 
			list = session.createQuery(jpqlQuery, Team.class)
					.setParameter("abbr", abr)
					.getResultList();
			transaction.commit();
		}catch(RuntimeException e) {
			if(transaction != null)
				transaction.rollback();
			throw e;
		}
		return list;
	}

	//Method to find out the teams , who need players with max age of the player> specified age
	@Override
	public List<Team> getAllTeamDetailsBySpecifiedAge(int specifiedAge) {
		List<Team> teams = null;
		//get session from SF
		Session session = getSessionFactory().getCurrentSession();
		//begin transaction
		Transaction transaction = session.beginTransaction();
		try {
			//create jpql query
			String jpqlQuery = "select t from Team t where maxAge > :age";
			teams = session.createQuery(jpqlQuery, Team.class) //parameters are: jpql query and result type class(here it is Team as all details are selected)
			.setParameter("age", specifiedAge)
			.getResultList();
			
			transaction.commit();
		}catch(RuntimeException e) {
			if(transaction != null)
				transaction.rollback();
			throw e;
		}
		return teams;
	}
	
	//Method to update the team's no of wickets taken n batting avg
	@Override
	public String updateTeamDetails(Serializable id, double battingAvg, int noOfWickets) {
		String msg ="Table data not updated";
		//get session from SF
		Session session = getSessionFactory().getCurrentSession();
		//begin a transaction
		Transaction transaction = session.beginTransaction();
		try {
			Team team = session.get(Team.class, id);
			team.setMinBattingAvg(battingAvg);
			team.setMinWicketsTaken(noOfWickets);
			transaction.commit(); 
			// session.flush => 1. hibernate performs automatically dirty checking i.e. check status of DB and L1 cache 
						//2. if status is not PERSISTENT then it does update/insert/delete operation as per required
						//3. session.close => L1 cache destroyed and pooled out DB conn returned to connection pool
			msg = "Table data : batting average and no of wickets updated";
		}
		catch(RuntimeException e) {
			if(transaction != null)
				transaction.rollback();
			throw e;
		}
		return msg;
	}
}
