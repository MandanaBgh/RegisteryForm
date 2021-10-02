<%--
  Created by IntelliJ IDEA.
  User: Mandana
  Date: 10/2/2021
  Time: 5:13 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>Add New Customer</title>
</head>
<body>
<h3>New Customer</h3>

<form:form action="saveCustomer" modelAttribute="customer" method="POST">

    <table>
        <tbody>
        <tr>
            <td><label>First name:</label></td>
            <td><form:input path="name"/></td>
        </tr>

        <tr>
            <td><label>Last name:</label></td>
            <td><form:input path="lastname"/></td>
        </tr>

        <tr>
            <td><label>Image:</label></td>
            <td><form:input path="image"/></td>
        </tr>
        <tr>
            <td><label>BirthDay:</label></td>
            <td><form:input path="birthday"/></td>
        </tr>
        <tr>
            <td><label></label></td>
            <td><input type="submit" value="Save" class="save"/></td>
        </tr>


        </tbody>
    </table>


</form:form>
<p>
    <a href="${pageContext.request.contextPath}">Back to List</a>
</p>
</body>
</html>