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
<title>Edit User Details</title>
</head>
<body>
        <form:form action="user" method="post" commandName="userForm">
            <table>
                <tr>
                    <td colspan="3" align="center"><h2>User Details</h2></td>
                </tr>
                <tr>
                    <td>First Name:</td>
                    <td><form:input path="firstName" cssErrorClass=".error"/></td>
                    <td><form:errors path="firstName" /></td>
                </tr>
                <tr>
                    <td>Last Name:</td>
                    <td><form:input path="lastName" cssErrorClass=".error"/></td>
                    <td><form:errors path="lastName" /></td>
                </tr>
                <tr>
                    <td>Address Line 1:</td>
                    <td><form:input path="address.line1" cssErrorClass=".error"/></td>
                    <td><form:errors path="address.line1" /></td>
                </tr>
                <tr>
                    <td>Address Line 2:</td>
                    <td><form:input path="address.line2" cssErrorClass=".error"/></td>
                    <td><form:errors path="address.line2" /></td>
                </tr>
                <tr>
                    <td>City:</td>
                    <td><form:input path="address.city" cssErrorClass=".error"/></td>
                    <td><form:errors path="address.city" /></td>
                </tr>
                <tr>
                    <td>State:</td>
                    <td><form:input path="address.state" cssErrorClass=".error"/></td>
                    <td><form:errors path="address.state" /></td>
                </tr>
                <tr>
                    <td>Zip:</td>
                    <td><form:input path="address.zip" cssErrorClass=".error"/></td>
                    <td><form:errors path="address.zip" /></td>
                </tr>
                <tr>
                    <td>Date of Birth:</td>
                    <td><form:input path="dob" cssErrorClass=".error"/></td>
                    <td><form:errors path="dob" /></td>
                </tr>
                <tr>
                    <td colspan="3" align=left><input type="submit" value="Save" /></td>
                </tr>
            </table>
        </form:form>
</body>
</html>