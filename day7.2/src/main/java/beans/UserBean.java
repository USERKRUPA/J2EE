package beans;

import java.sql.Date;
//import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;

import dao.UserDaoImpl;
import pojos.User;

public class UserBean {
//props 
	private UserDaoImpl userDao;// dependency
	private User userDetails;// result
	// clnt info
	private String em;
	private String pass;
	private String fname;
	private String lname;
	private String dob;

	

	// def ctor
	public UserBean() throws SQLException {
		// create user dao instance
		userDao = new UserDaoImpl();
		System.out.println("user bean created...");
	}

	public UserBean(String em, String pass, String fname, String lname, String dob) {
		super();
		this.em = em;
		this.pass = pass;
		this.fname = fname;
		this.lname = lname;
		this.dob = dob;
	}

	public UserBean(UserDaoImpl userDao, User userDetails, String em, String pass, String fname, String lname,
			String dob) {
		super();
		this.userDao = userDao;
		this.userDetails = userDetails;
		this.em = em;
		this.pass = pass;
		this.fname = fname;
		this.lname = lname;
		this.dob = dob;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getFname() {
		return fname;
	}
	
	

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getEm() {
		return em;
	}

	// generate getter n setter
	public UserDaoImpl getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDaoImpl userDao) {
		this.userDao = userDao;
	}

	public User getUserDetails() {
		return userDetails;
	}

	public void setUserDetails(User userDetails) {
		this.userDetails = userDetails;
	}

	public String getEmail() {
		return em;
	}

	public void setEm(String email) {
		this.em = email;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	// add a B.L method to authenticate user
	public String authenticateUser() throws SQLException {
		System.out.println("in JB B.L auth user " + em + " " + pass);
		// JB ---> DAo's method
		userDetails = userDao.authenticateUser(em, pass);
		if (userDetails == null) // => invalid login
			return "login"; // JB rets dyn navigational outcome (i.e telling JSP where to take the clnt
							// next)
		// => login successful
		if (userDetails.getRole().equals("admin"))
			return "admin_page";
		// => voter
		if (userDetails.isVotingStatus())
			return "logout";
		// => voter not yet vote
		return "candidate_list";
	}

	public String registerNewVoter() throws SQLException {
		System.out.println(dob);
		//if(Period.between((Date.valueOf(dob)).toLocalDate(),  LocalDate.now()).getYears() < 21)
		//	throw new SQLException("Age Limit is 21 Years!");
		
		String newUser = userDao.registerNewVoter(new User(fname, lname, em, pass, Date.valueOf(dob)));
		return newUser;
	}

}
