package pages;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDaoImpl;
import pojos.Candidate;


@WebServlet("/admin_page")
public class AdminPage extends HttpServlet{
	//doGet
	@Override
	public void doGet(HttpServletRequest rq,HttpServletResponse rs) throws ServletException,IOException
	{
		System.out.println("in admin page ");
		HttpSession hs = rq.getSession();
		//1. set resp cont type
		rs.setContentType("text/html");
		//2. get PW : to send resp from servlet ---> web clnt
		try(PrintWriter pw=rs.getWriter()) {
			pw.print("<h5>Welcome Admin!</h5>");
			//generate dyn cont (add dyn contents to buffer of PW
			UserDaoImpl userDao = (UserDaoImpl) hs.getAttribute("user_dao");
			
			List<Candidate> list = userDao.getHighestCandidate();
			if(list != null) {
			
				for(Candidate c: list)
				{
					pw.print("<h5>"+c.getCandidateName()+"</h5>");
				}
				
			}
			else
				pw.print("<h5>Issue while  getting candidate list!!</h5>");
				
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
