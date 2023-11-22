<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<%--Invoke all matching setters --%>
<jsp:useBean id="user" class="beans.UserBean"></jsp:useBean>
<jsp:setProperty property="*" name="user"/>

<body>
<%--Invoke B>L methodes for voter reg --%>
<h5>Status:${sessionScope.user.registerNewVoter()}</h5>
</body>
</html>