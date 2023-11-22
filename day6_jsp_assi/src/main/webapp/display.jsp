<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Display User Details</title>
</head>
<body>
	${
		sessionScope.user_dtls
	 }
	 <h3><a href ='logout.jsp'>Log me out</a></h3>
</body>
</html>