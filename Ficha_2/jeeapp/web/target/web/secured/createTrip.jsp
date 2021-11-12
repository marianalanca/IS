<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Create trip</title>
</head>
<body>

    <strong>Create Trip</strong>
    <p>${auth}</p>
    <!--%= request.getContextPath() %>/-->
    <form action="<%= request.getContextPath() %>/createTrip" method="get">
        Departure Point: <input name="departure_point" type="text" placeholder="departure point..." /> <br />
        Destination:<input name="destination" type="text" placeholder="destination..." /> <br />
        Price: <input name="price" type="text" placeholder="price..." /> <br />
        Capacity: <input name="capacity" type="text" placeholder="capacity..." /> <br />
        Departure Date: <input name="departure_date" type="datetime-local"/> <br />
        <input type="submit">
    </form>

</body>
</html>
