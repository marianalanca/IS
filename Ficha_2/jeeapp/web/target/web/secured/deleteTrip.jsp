<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Delete Trip</title>
</head>
<body>
    <strong>Delete Trip</strong>
    <p>${auth}</p>

    <c:forEach itens="${trips}" var = "trip">
        <c:out value="Departure point: ${trip.departure_point}\nDestination: ${trip.destination}\nDate: ${trip.departure_date}">
        </c:out>
        <form action="<%= request.getContextPath() %>/deleteTrip" method="post">
            <input type="text" value="${trip.id}" name="trip_id" hidden="hidden">
            <input type="submit">
        </form>
    </c:forEach>
</body>
</html>
