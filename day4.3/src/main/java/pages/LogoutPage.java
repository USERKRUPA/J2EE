package pages;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CandidateDaoImpl;
import dao.UserDaoImpl;
import pojos.User;

@WebServlet("/logout")
public class LogoutPage extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// doGet
	@Override
	public void doGet(HttpServletRequest rq, HttpServletResponse rs) throws ServletException, IOException {
		System.out.println("in logout page ");
		// 1. set resp cont type
		rs.setContentType("text/html");
		// 2. get PW : to send resp from servlet ---> web clnt
		try (PrintWriter pw = rs.getWriter()) {
			// get the http session from WC
			HttpSession session = rq.getSession();

			// get user details from session scope
			User user = (User) session.getAttribute("clnt_details");

			if (user != null) {
				pw.print("<h4>Hello, " + user.getFirstName() + "</h4>");
				
				// check status of user -- i.e. user casted vote or not
				if (user.isVotingStatus())
					// vote casted already
					pw.print("<h4>You have alreadycasted vote</h4>");
				else {
					// get user dao from session scope
					UserDaoImpl userDao = (UserDaoImpl) session.getAttribute("user_dao");

					// get candidate dao from session scope
					CandidateDaoImpl candidateDao = (CandidateDaoImpl) session.getAttribute("candidate_dao");
					
					// set status to true(1)
					if (userDao.updateStatus(user.getUserId()) != null)
						// increment vote of particular candidate
						pw.print("<h5>"+candidateDao.incrementVote(Integer.parseInt(rq.getParameter("candidate_id")))+"</h5>");
				}
			}
			else 
				pw.print("<h4> Session Tracking failed !!!!!!! No cookies.....</h4>");
			session.invalidate();
			pw.print("<h4>Thank you for casting a vote...Logout successfully</h4>");
		} catch (Exception e) {
			throw new RuntimeException("SQL Exception " + getClass(), e);
		}
	}

}
