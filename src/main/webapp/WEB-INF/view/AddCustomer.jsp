<%--
  Created by IntelliJ IDEA.
  User: Mandana
  Date: 10/2/2021
  Time: 5:13 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<link href="${pageContext.request.contextPath}/resources/css/persianDatepicker-default.css" type="text/css"
      rel="stylesheet"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-1.10.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/persianDatepicker.min.js"></script>

<html>
<head>
    <title>Add New Customer</title>
</head>
<body>
<h3>New Customer</h3>
<input type="text" id="input1"/>
<span id="span1"></span>


<p>
    <a href="${pageContext.request.contextPath}/Home">Back to List</a>
</p>
</body>
</html>

<script type="text/javascript">
    $(function () {
        $("#input1, #span1").persianDatepicker();
    });
</script>