<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>List Passengers</title>
</head>
<body>

<a href="${pageContext.request.contextPath}/index.jsp"> Log out </a> <br />

<form action="${pageContext.request.contextPath}/redirect" method="get">
    <input value="Menu" name="CM" type="submit">
</form> <br>

<p>${auth}</p>

    <h1>List Passengers</h1> <br/>

    <c:choose>
        <c:when test="${empty passengersList}"> There is no passengers in this trip </c:when>
        <c:otherwise>
                <c:forEach var="item" items="${passengersList}">
                    <c:out value="Name: ${item.clientName}">
                    </c:out><br>
                </c:forEach>
        </c:otherwise>
    </c:choose>
</body>
</html>
