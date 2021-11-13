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

    <c:forEach items="${trips}" var = "trip">
        <c:out value="Departure point: ${trip.departure_point}Destination: ${trip.destination}Date: ${trip.departure_date}">
        </c:out>
        <form action="<%= request.getContextPath() %>/deleteTrips" method="post">
            <input type="text" value="${trip.id}" name="trip_id" hidden="hidden">
            <input type="submit">
        </form>
    </c:forEach>
</body>
</html>
