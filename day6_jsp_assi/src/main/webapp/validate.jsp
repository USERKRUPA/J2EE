<%@page import="java.io.ObjectOutputStream.PutField"%>
<%@page import="com.mysql.cj.protocol.x.SyncFlushDeflaterOutputStream"%>
<%@page import="pojos.User"%>
<%@page import="java.util.HashMap"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User Validation</title>
</head>
<%!
//jsp declartion block to add variables n methods(implicit : private)
HashMap<String, User> userMap;
public void jspInit(){
	System.out.println("in init" + Thread.currentThread());
	userMap = new HashMap<>();
	userMap.put("Rama Vaidya",new User("Rama Vaidya","1234",64));
	userMap.put("Sakshi Gaikwad",new User("Sakshi Gaikwad","12345",22));
}
%>
<body>
	<% 
	System.out.println("in scriplet" + Thread.currentThread());
	String name = request.getParameter("name");
	String password = request.getParameter("pass");
	User user = userMap.get(name);
	if(user != null){
		//user exists
		if(user.getPassword().equals(password)){
			//password is matched
			
			session.setAttribute("user_dtls", user);
			//Redirect to display.jsp
			response.sendRedirect("display.jsp");
		}
		else{
			//password doesn't match
			%>
			<!--  out.write("Invalid Password<h4><a href ='login.jsp'>Retry to login</a></h4>");-->
			<h4 style="color:red;">Invalid Password <br/><a href ='login.jsp'>Retry to login</a></h4>
		<%		
		}
	}
	else{
		//user doesn't exist
	%>
		<!-- out.write("Invalid Password<h4><a href ='register.jsp'>User Doesn't exist...Click here to Register</a></h4>"); -->
		<h4 style="color:purple;">User Doesn't exist...<a href ="register.jsp">Click here to Register</a></h4>
	<%
	}
	%>
</body>
<%!
public void jspDestroy(){
	System.out.println("in destroy" + Thread.currentThread());	
}
%>
</html>