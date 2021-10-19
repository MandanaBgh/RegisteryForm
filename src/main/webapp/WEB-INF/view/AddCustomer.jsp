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

<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<link rel="stylesheet" href="<c:url value="/resources/css/persian-datepicker.css" />">
<script type="text/javascript" src="<c:url value="/resources/js/jquery.min.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/js/persian-datepicker.js" />"></script>

<script type="text/javascript">
    $(function () {

        $('#datepicker0').datepicker();
    })
</script>
<style>
    .error {
        color: red;
    }
</style>

<html>
<head>
    <title>Add New Customer</title>
</head>


<h3>New Customer</h3>

<form:form modelAttribute="customer" method="POST" action="saveCustomer" enctype="multipart/form-data"
           processData="false" contentType="false" cache="false">
    <form:hidden path="id"/>
    <table>
        <tbody>
        <tr>
            <td><label>First name(*):</label></td>
            <td><form:input path="name"/></td>
            <form:errors path="name" cssClass="error"/>

        </tr>

        <tr>
            <td><label>Last name:</label></td>
            <td><form:input path="lastname"/></td>
            <form:errors path="lastname" cssClass="error"/>
        </tr>

        <tr>
            <td><label>Image:</label></td>
            <td><input type="file" name="photo"/></td>
        </tr>
        <tr>
            <td><label>BirthDay:</label></td>
            <td>
                <fmt:formatDate value="${customer.birthday}" type="date"
                                pattern="yyyy/MM/dd"
                                var="theFormattedDate"/>
                <form:input path="birthday" value="${theFormattedDate}" id="datepicker0"/>

            </td>

            </td>
            </td>
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

