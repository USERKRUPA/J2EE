package pages;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CandidateDaoImpl;
import pojos.Candidate;
import pojos.User;

@WebServlet("/admin_page")
public class AdminPage extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// doGet
	@Override
	public void doGet(HttpServletRequest rq, HttpServletResponse rs) throws ServletException, IOException {
		HttpSession session = rq.getSession();
		System.out.println("in admin page ");
		// 1. set resp cont type
		rs.setContentType("text/html");
		// 2. get PW : to send resp from servlet ---> web clnt
		try (PrintWriter pw = rs.getWriter()) {
			// generate dyn cont (add dyn contents to buffer of PW

			// get clnt_details from session scope
			User user = (User) session.getAttribute("clnt_details");
			// down casting :: Object to UserDao

			if (user != null) {
				pw.print("<h5>Welcome Admin," + user.getFirstName() + "</h5>");

				// get candidate details from candidate dao attribute of session
				CandidateDaoImpl candidateDao = (CandidateDaoImpl) session.getAttribute("candidate_dao");
				// down casting :: Object to CandidateDao

				// show top 2 candidates having max votes
				List<Candidate> candidates = candidateDao.getTop2Candidates();
				pw.print("<h3>Top 2 candidates are: </h3>");
				pw.print("<h3>Name&nbsp;Votes</h3>");
				
				//in case candidates table is empty => candidates list is empty 
				if (candidates != null)
					candidates
							.forEach(c -> pw.print("<h5> " + c.getCandidateName() + "      " + c.getVotes() + "</h5>"));
				else
					pw.print("No data available");

				// display vote analysis party wise
				Map<String, Integer> partyMap = candidateDao.partyWiseVoteAnalysis();
				pw.print("<h3>Vote analysis party wise: </h3>");
				pw.print("<h3>Party Name&nbsp;&nbsp;Votes</h3>");
				
				////in case candidates table is empty => partyMap is empty 
				if (partyMap != null)
					partyMap.forEach((k, v) -> pw.print("<h5>" + k + "     " + v + "</h5>"));
				else
					pw.print("No data available");
			} else
				pw.print("<h4>Session Tracking failed.... No cookies.....</h4>");
			// discard http session
			session.invalidate();

			// logout msg for admin
			pw.print("<h5>Logout from session</h5>");

			// visit again link
			pw.print("<h5><a href=login.html>Visit again</a></h5>");
		} catch (Exception e) {
			throw new ServletException("Err in admin page" + getClass(), e);
		}
	}

}
