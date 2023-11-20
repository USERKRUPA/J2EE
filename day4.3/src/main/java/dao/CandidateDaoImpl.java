package dao;

import static utils.DBUtils.getCn;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pojos.Candidate;

public class CandidateDaoImpl implements CandidateDao {
	private Connection cn;
	private PreparedStatement pst1;
	private PreparedStatement pstUpdate;
	private PreparedStatement pstSelectTop2;
	private PreparedStatement pstSelectVoteAnalysis;

	public CandidateDaoImpl() throws SQLException {
		// open cn
		cn = getCn();
		// pst1 : to get all candidates
		pst1 = cn.prepareStatement("select * from candidates");

		// pstUpdate : to increment vote
		pstUpdate = cn.prepareStatement("update candidates set votes = votes + 1 where id = ?");

		// pstSelectTop2 : to get top 2 candidates having max votes
		pstSelectTop2 = cn.prepareStatement("select * from candidates order by votes desc limit 2");
		
		// pstSelectTop2 : to get top 2 candidates having max votes
		pstSelectVoteAnalysis = cn.prepareStatement("select party, sum(votes) from candidates group by party");
		System.out.println("candidate dao created....");
	}

	@Override
	public String incrementVote(int candidateId) throws SQLException {
		pstUpdate.setInt(1, candidateId);
		int rows = pstUpdate.executeUpdate();
		if(rows == 1)
			return "Votes incremented";
		return "Vote incerement failed";
	}

	@Override
	public List<Candidate> getAllCandidates() throws SQLException {
		// create empty List
		List<Candidate> candidates = new ArrayList<>();
		try (ResultSet rst = pst1.executeQuery()) {
			while (rst.next())
				candidates.add(new Candidate(rst.getInt(1), rst.getString(2), rst.getString(3), rst.getInt(4)));
		}
		return candidates;
	}

	public void cleanUp() throws SQLException {
		if (pst1 != null)
			pst1.close();
		if(pstUpdate != null)
			pstUpdate.close();
		if(pstSelectTop2 != null)
			pstSelectTop2.close();
		if(pstSelectVoteAnalysis != null)
			pstSelectVoteAnalysis.close();

		System.out.println("candidate dao cleaned up....");
	}

	public List<Candidate> getTop2Candidates() throws SQLException {
		List<Candidate> candidates = new ArrayList<>();
		try(ResultSet rst = pstSelectTop2.executeQuery()){
			while(rst.next())
				candidates.add(new Candidate(rst.getInt(1), rst.getString(2), rst.getString(3), rst.getInt(4)));
		}
		
		return candidates;
	}

	public Map<String, Integer> partyWiseVoteAnalysis() throws SQLException {
		Map<String, Integer> partyMap = new HashMap<>();
		try(ResultSet rst = pstSelectVoteAnalysis.executeQuery()){
			while(rst.next())
				partyMap.put(rst.getString(1), rst.getInt(2));
		}
		return partyMap;
	}

}
