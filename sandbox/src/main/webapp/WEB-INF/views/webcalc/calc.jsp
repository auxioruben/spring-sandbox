<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page session="false" %>
<html>
<head>
	<title>Webcalc</title>
	<style>
	.error { color: #ff0000;}
	</style>
</head>
<body>
        <form:form action="eval" method="post" commandName="calcForm">
            <table>
                <tr>
                    <td colspan="3" align="center"><h2>Web Calculator</h2></td>
                </tr>
                <tr>
                    <td>Expression:</td>
                    <td><form:input path="expression" cssErrorClass=".error"/></td>
                    <td><form:errors path="expression" /></td>
                </tr>
                <tr>
                    <td>Result</td>
                    <td colspan="2"><form:input path="result" readonly="true" /></td>
                </tr>
                <tr>
                    <td colspan="3" align=left><input type="submit" value="Evaluate" /></td>
                </tr>
            </table>
        </form:form>

</body>
</html>
