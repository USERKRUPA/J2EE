package pages;

import static utils.DBUtils.openConnection;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.TeamDao;
import dao.TeamDaoImpl;

@WebServlet("/player")
public class AddPlayerForm extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// private PlayerDao playerDao;
	private TeamDao teamDao;

	public void init(ServletConfig config) throws ServletException {
		try {
			openConnection();
			// playerDao = new PlayerDaoImpl();
			teamDao = new TeamDaoImpl();
			System.out.println("Initialization done");
		} catch (Exception e) {
			throw new ServletException("Err in init " + getClass(), e);
		}
	}

	public void destroy() {
		try {
			teamDao.cleanup();
			// playerDao.cleanUp();
			System.out.println("Clenup done");
		} catch (Exception e) {
			throw new RuntimeException("Err in destroy" + getClass(), e);
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1. set response content type
		response.setContentType("text/html");
		// 2. get PrintWriter ref
		try (PrintWriter pw = response.getWriter()) {
			pw.print("<form method='post' action='addplayer'>");
			pw.print("<fieldset><legend>Add a player to IPL team	</legend><table><tr>"
					+ "<td><label for='iplteam'>Choose a team</label></td>" 
					+ "<td><select name='iplteam' id='iplteam'>");
			List<String> abbrevations = teamDao.getTeamAbbrevations();
			for(String abbr : abbrevations) {
				pw.print("<option value=" +abbr +">"+abbr+"</option>");
			}
			pw.print("<select>");
			pw.print("</td></tr><tr><td><label for='name'>Name</label></td>"
					+ "<td><input type='text' name='name' id='name' required /></td>"
					+ "</tr><tr><td><label for='dob'>DoB</label></td>"
					+ "<td><input type='date' name='dob' id='dob' required /></td>"
					+ "</tr><tr><td><label for='battingavg'>Batting Average</label></td>"
					+ "<td><input type='text' name='battingavg' id='battingavg' required /></td>"
					+ "</tr><tr><td><label for='wickets'>Wickets Taken</label></td>"
					+ "<td><input type='text' name='wickets' id='wickets' required /></td>\r\n"
					+ "</tr><tr><td colspan='2'>"
					+ "<input type='submit' name='submit' value='Add Player'/>"
					+ "</td></tr></table></fieldset></form>");
		} catch (Exception e) {
			throw new ServletException("Err in do post " + getClass(), e);
		}
	}

}
