<%--
  Created by IntelliJ IDEA.
  User: Mandana
  Date: 10/2/2021
  Time: 5:13 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<link rel="stylesheet" href="<c:url value="/resources/css/persianDatepicker-default.css" />">
<script type="text/javascript" src="<c:url value="/resources/js/jquery-1.10.1.min.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/js/persianDatepicker.min.js" />"></script>

<script type="text/javascript" src="<c:url value="/resources/js/function.js" />"></script>
<html>
<head>
    <title>Add New Customer</title>
</head>


<h3>New Customer</h3>
<form:form modelAttribute="customer" method="POST" action="saveCustomer">
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
            <td><form:input path="birthday" id="input1"/><span id="span1"></span></td>
        </tr>
        <tr>
            <td><label></label></td>
            <td><input type="submit" value="Save" class="save"/></td>
        </tr>


        </tbody>
    </table>

</form:form>

<p>
    <a href="${pageContext.request.contextPath}/Home">Back to List</a>
</p>

</body>
</html>

