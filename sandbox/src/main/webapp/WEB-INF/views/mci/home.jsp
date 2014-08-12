<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>MCI</title>
</head>
<body>

<c:choose>
	<c:when test="${pageContext.request.userPrincipal.name != null}">
		<h1>
			Welcome : ${pageContext.request.userPrincipal.name} | <a href="/sandbox/login?logout"> Logout</a>
		</h1>
	</c:when>
	<c:otherwise>
		<h1>
			Welcome to Monolith Conglomerate International. | <a href="/sandbox/login">Login</a>  
		</h1>
	</c:otherwise>
</c:choose>
	
<p><a href="/sandbox/mci/about">About</a></p>
<p><a href="/sandbox/mci/user">Edit Account Details</a></p>
<p><a href="/sandbox/mci/admin">Admin</a></p>
<hr>
<P>  The time on the server is ${serverTime}. </P>
<p><a href="/sandbox">Return to Sandbox</a></p>
</body>
</html>
