<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Logout User</title>
</head>
<body>
	<h4>Hello, ${sessionScope.user_dtls.getName()} </h4>
	<% 
	session.invalidate();
	%>
	<h3>Thank you for visiting our website. <br/>
	<a href="index.jsp">Visit Again</a>
	</h3>
</body>
</html>