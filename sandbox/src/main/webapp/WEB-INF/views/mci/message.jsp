<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>MCI Administration</title>
</head>
<body>
	<c:if test="${not empty head}">
		<h1>${head}</h1>
	</c:if>
	<c:if test="${not empty msg}">
		<h2>${msg}</h2>
	</c:if>
	
<p><a href="/sandbox/logout">Logout</a></p>
<hr>
<P>  The time on the server is ${serverTime}. </P>
<p><a href="/sandbox">Return to Sandbox</a></p>
<p><a href="/sandbox/mci">Return to MCI home</a></p>
</body>
</html>