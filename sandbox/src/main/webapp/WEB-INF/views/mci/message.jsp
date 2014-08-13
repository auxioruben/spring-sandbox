<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>${title}</title>
</head>
<body>
	<c:if test="${not empty head}">
		<h2>${head}</h2>
	</c:if>
	<c:if test="${not empty msg}">
		<p>${msg}</p>
	</c:if>

	<c:choose>
		<c:when test="${pageContext.request.userPrincipal.name != null}">
			<p><a href="/sandbox/logout">Logout</a></p>
		</c:when>
		<c:otherwise>
			<p><a href="/sandbox/login">Login</a></p>
		</c:otherwise>
	</c:choose>
<hr>
<P>  The time on the server is ${serverTime}. </P>
<p><a href="/sandbox">Return to Sandbox</a></p>
<p><a href="/sandbox/mci">Return to MCI home</a></p>
</body>
</html>