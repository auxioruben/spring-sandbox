<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>MCI</title>
</head>
<body>

<c:choose>
	<c:when test="${pageContext.request.userPrincipal.name != null}">
		<h2>
			Welcome ${pageContext.request.userPrincipal.name} 
		</h2>
		<p><a href="/sandbox/logout">Logout</a></p>
	</c:when>
	<c:otherwise>
		<h2>
			Welcome to Monolith Conglomerate International.   
		</h2>
		<p><a href="/sandbox/login">Login</a></p>
	</c:otherwise>
</c:choose>
	
<p><a href="/sandbox/mci/about">About</a></p>
<p><a href="/sandbox/mci/user">User Page</a></p>
<p><a href="/sandbox/mci/admin">Admin Page</a></p>
<hr>
<P>  The time on the server is ${serverTime}. </P>
<p><a href="/sandbox">Return to Sandbox</a></p>
</body>
</html>
