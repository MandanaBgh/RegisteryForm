<%--
  Created by IntelliJ IDEA.
  User: Mandana
  Date: 9/30/2021
  Time: 8:54 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Welcome Page</title>
</head>
<body>
<h1>Welcome to Registration Page</h1>
<p>
    <a href="${pageContext.request.contextPath}/AddNewCustomer">Add New Customer</a>
</p>
<table>
    <tr>
        <th>First Name</th>
        <th>Last Name</th>
        <th>Image</th>
        <th>BirthDay</th>
    </tr>

    <!-- loop over and print our customers -->
    <c:forEach var="tempCustomer" items="${customers}">

        <tr>
            <td> ${tempCustomer.name} </td>
            <td> ${tempCustomer.lastname} </td>
            <td><img src="imageDisplay?id=${tempCustomer.id}" height="60px" width="60px"/></td>
            <td><fmt:formatDate pattern="yyyy/MM/dd" value="${tempCustomer.birthday}"/></td>
        </tr>

    </c:forEach>

</table>

</body>
</html>