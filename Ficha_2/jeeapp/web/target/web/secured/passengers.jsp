<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>List Passengers</title>
</head>
<body>

    <strong>List Passengers</strong> <br/>

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
