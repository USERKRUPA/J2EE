package dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import pojos.Candidate;

public interface CandidateDao {
//add a method to get all candidate details
	List<Candidate> getAllCandidates() throws SQLException;
	
	//add a method to increment vote of particular candidate
	String incrementVote(int candidateId) throws SQLException;
	
	//add a method to retrieve top 2 candidates having maximum votes
	List<Candidate> getTop2Candidates() throws SQLException;
	
	//add a method to display party wise vote analysis
	Map<String, Integer> partyWiseVoteAnalysis() throws SQLException;
}
