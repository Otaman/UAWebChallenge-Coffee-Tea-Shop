<%--
  Created by Bohdan on 19.03.2015.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Countries</title>
</head>
<body>
<h1>All Countries:</h1>
<table border="1">
    <c:forEach var="country" items="${countries}">
        <tr>
            <td>${country.id}</td>
            <td>${country.name}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
