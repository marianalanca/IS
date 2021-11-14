<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Delete Trip</title>
</head>
<body>
    <a href="${pageContext.request.contextPath}/secured/displayCM.jsp"> Menu </a> <br />
    <p>${auth}</p>

    <strong>Delete Trip</strong>

    <c:choose>
        <c:when test="${empty trips}"> There is no future trips </c:when>
        <c:otherwise>
        <c:forEach items="${trips}" var = "trip">
            <c:out value="Departure point: ${trip.departure_point}">
            </c:out>
            <c:out value="Destination: ${trip.destination}">
            </c:out>
            <c:out value="Date: ${trip.departure_date} Price: ${trip.price}">
            </c:out>
            <form action="<%= request.getContextPath() %>/deleteTrips" method="post">
                <input type="text" value="${trip.id}" name="trip_id" hidden="hidden">
                <input type="submit">
            </form>
        </c:forEach>
        </c:otherwise>
    </c:choose>
</body>
</html>
