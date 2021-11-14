<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>List Trips</title>
</head>
<body>
<a href="${pageContext.request.contextPath}/secured/displayCM.jsp"> Menu </a> <br />
<p>${auth}</p>

<strong>Trips</strong><br/>
    <c:choose>
        <c:when test="${empty trips}"> There is no trips </c:when>
        <c:otherwise>
            <c:forEach items="${trips}" var = "trip">
                <c:out value="Departure point: ${trip.departure_point}">
                </c:out> <br/>
                <c:out value="Destination: ${trip.destination}">
                </c:out><br/>
                <c:out value="Date: ${trip.departure_date} Price: ${trip.price}">
                </c:out><br/>
                <form action="<%= request.getContextPath() %>/selectDate" method="post">
                    <input type="text" value="${trip.id}" name="trip_id" hidden="hidden">
                    <input type="submit">
                </form>
                <br>
            </c:forEach>
        </c:otherwise>
    </c:choose>
</body>
</html>
