<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Create trip</title>
</head>
<body>

<a href="${pageContext.request.contextPath}/index.jsp"> Log out </a> <br />
<br>
<form action="${pageContext.request.contextPath}/redirect" method="get">
    <input value="Menu" name="CM" type="submit">
</form>

<p>${auth}</p>

    <h1>Create Trip</h1>
    <p>${auth}</p>
    <!--%= request.getContextPath() %>/-->
    <form action="<%= request.getContextPath() %>/createTrip" method="post">
        Departure Point: <input required="" name="departure_point" type="text" placeholder="departure point..." /> <br />
        Destination:<input required="" name="destination" type="text" placeholder="destination..." /> <br />
        Price: <input required="" name="price" type="text" placeholder="price..." /> <br />
        Capacity: <input required="" name="capacity" type="text" placeholder="capacity..." /> <br />
        Departure Date: <input required="" name="departure_date" type="datetime-local"/> <br />
        <input type="submit">
    </form>

</body>
</html>
