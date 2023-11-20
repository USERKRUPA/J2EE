package pages;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.PlayerDao;
import dao.PlayerDaoImpl;
import dao.TeamDao;
import dao.TeamDaoImpl;
import pojos.Player;
import pojos.Team;

import static utils.DBUtils.*;

@WebServlet(urlPatterns = "/addplayer", loadOnStartup = 1)
public class ValidateAndAddPlayerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private PlayerDao playerDao;
	private TeamDao teamDao;

	public void init(ServletConfig config) throws ServletException {
		try {
			openConnection();
			playerDao = new PlayerDaoImpl();
			teamDao = new TeamDaoImpl();
			System.out.println("Initialization done");
		} catch (Exception e) {
			throw new ServletException("Err in init " + getClass(), e);
		}
	}

	public void destroy() {
		try {
			teamDao.cleanup();
			playerDao.cleanUp();
			System.out.println("Clenup done");
		} catch (Exception e) {
			throw new RuntimeException("Err in destroy" + getClass(), e);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1. set response content type
		response.setContentType("text/html");
		// 2. get PrintWriter ref
		try (PrintWriter pw = response.getWriter()) {
			// 3. add dynamic content to page
			String iplTeam = request.getParameter("iplteam");
			String name = request.getParameter("name");
			String[] fullnm = name.split(" ");
			Date dob = Date.valueOf(request.getParameter("dob"));
			int bat_avg = Integer.valueOf(request.getParameter("battingavg"));
			int wkt = Integer.valueOf(request.getParameter("wickets"));
			
			Team team = teamDao.getSpecificTeamDetails(iplTeam);
			
			if((Period.between(dob.toLocalDate(), LocalDate.now())).getYears() <= team.getMax_age()
					&& bat_avg >= team.getBatting_avg() && wkt >= team.getWickets_taken()) {
				//String first_name, String last_name, Date dob, double batting_avg, int wickets_taken,
				//int team_id
					pw.print("<h5>"+playerDao.insertPlayer(new Player(fullnm[0], fullnm[1], dob, bat_avg, wkt), team.getTeam_id())+"</h5>");
			}
			else
				pw.printf("<h5>Player Rejected. Player doesn't meet team criteria</h5>");
		} // PrintWriter is autoclosable
		catch (Exception e) {
			throw new ServletException("Err in do post " + getClass(), e);
		}
	}
}
