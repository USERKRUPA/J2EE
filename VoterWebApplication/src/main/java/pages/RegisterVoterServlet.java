package pages;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDaoImpl;
import pojos.User;

@WebServlet("/signup")
public class RegisterVoterServlet extends HttpServlet {
	UserDaoImpl userDao;

	@Override
	public void init() throws ServletException {
		try {
			// creating dow instance to establish connection
			userDao = new UserDaoImpl();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ServletException(getServletInfo(), e);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		resp.setContentType("text/html");
		try (PrintWriter pw = resp.getWriter()) {
			// first_name , last_name , email, password , dob
			String first_name = req.getParameter("fname");
			String last_name = req.getParameter("lname");
			String email = req.getParameter("em");
			String password = req.getParameter("pass");
			Date dob = Date.valueOf(req.getParameter("dob"));
			LocalDate currDate = LocalDate.now();
			int age = Period.between(dob.toLocalDate(), currDate).getYears();
			System.out.println("age == " + age);
			try {
				if (age > 21) {
					List<String> em = userDao.getEmails();
					if (em.contains(email)) {
						pw.print("<h5> Duplicate Email!! Please Enter Unique Email.</h5>");
						pw.print("<a href='register.html'>Go To Registration Page</a>");
						throw new ServletException("Invalid age");
					}
					pw.print("<h5>" + userDao.registerNewVoter(new User(first_name, last_name, email, password, dob))
							+ " <h5>");
				} else {
					pw.print("<h5> Sorry!! You are not eligible for voting as your age is less than 21</h5>");
					throw new ServletException("Invalid age");
				}
			} catch (SQLException e) {
				pw.print("<h5> Sorry!! Duplicate Email id .Please enter unique email id.</h5>");
				pw.print("<a href='register.html'>Go To Registration Page</a>");
				e.printStackTrace();
				throw new ServletException("Sql excp");

			}

		}

	}

	@Override
	public void destroy() {

		try {// cleaning resources
			userDao.cleanUp();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
