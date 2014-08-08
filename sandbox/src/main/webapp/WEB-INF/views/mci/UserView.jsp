<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page session="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<style>
	.error { color: #ff0000;}
	</style>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
        <form:form action="eval" method="post" commandName="userForm">
            <table>
                <tr>
                    <td colspan="3" align="center"><h2>User</h2></td>
                </tr>
                <tr>
                    <td>First Name:</td>
                    <td><form:input path="expression" cssErrorClass=".error"/></td>
                    <td><form:errors path="expression" /></td>
                </tr>
                <tr>
                    <td>Last Name:</td>
                    <td><form:input path="expression" cssErrorClass=".error"/></td>
                    <td><form:errors path="expression" /></td>
                </tr>
                <tr>
                    <td>Address Line 1:</td>
                    <td><form:input path="expression" cssErrorClass=".error"/></td>
                    <td><form:errors path="expression" /></td>
                </tr>
                <tr>
                    <td>Address Line 2:</td>
                    <td><form:input path="expression" cssErrorClass=".error"/></td>
                    <td><form:errors path="expression" /></td>
                </tr>
                <tr>
                    <td>City:</td>
                    <td><form:input path="expression" cssErrorClass=".error"/></td>
                    <td><form:errors path="expression" /></td>
                </tr>
                <tr>
                    <td>State:</td>
                    <td><form:input path="expression" cssErrorClass=".error"/></td>
                    <td><form:errors path="expression" /></td>
                </tr>
                <tr>
                    <td>Zip:</td>
                    <td><form:input path="expression" cssErrorClass=".error"/></td>
                    <td><form:errors path="expression" /></td>
                </tr>
                <tr>
                    <td>Date of Birth:</td>
                    <td colspan="2"><form:input path="result" readonly="true" /></td>
                </tr>
                <tr>
                    <td colspan="3" align=left><input type="submit" value="Submit" /></td>
                </tr>
            </table>
        </form:form>



</body>
</html>