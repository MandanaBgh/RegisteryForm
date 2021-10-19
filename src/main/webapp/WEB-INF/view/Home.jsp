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
    <a href="${pageContext.request.contextPath}/showForm">Customer form</a>
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
        <c:url var="updateLink" value="/showFormForUpdate">
            <c:param name="customerId" value="${tempCustomer.id}"/>
        </c:url>
        <c:url var="deleteLink" value="/delete">
            <c:param name="customerId" value="${tempCustomer.id}"/>
        </c:url>
        <tr>
            <td> ${tempCustomer.name} </td>
            <td> ${tempCustomer.lastname} </td>
            <td><img src="${pageContext.request.contextPath}/imageDisplay/${tempCustomer.id}" height="100px"
                     width="100px"/>

            </td>
            <td><fmt:formatDate pattern="yyyy/MM/dd" value="${tempCustomer.birthday}"/></td>
            <td>
                <a href="${updateLink}">Update</a>
            </td>
            <td>
                <a href="${deleteLink}"
                   onclick="if (!(confirm('Are you sure you want to delete this customer?'))) return false">Delete</a>
            </td>
        </tr>

    </c:forEach>

</table>

</body>
</html>