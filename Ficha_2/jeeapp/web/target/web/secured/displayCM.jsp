<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Title</title>
</head>
<body>

<a href="${pageContext.request.contextPath}/index.jsp"> Log out </a> <br />

<p>Welcome ${auth}</p>

<h1>Menu</h1>

    <form action="${pageContext.request.contextPath}/redirect" method="get">
        <input value="Create trip" name="createTrip" type="submit">  <br />
        <input value="Delete trip" name="deleteTrip" type="submit">  <br />
        <input value="Top 5" name="top5" type="submit"> <br />
        <input value="Search Trips between dates" name="tripBetDate" type="submit"> <br />
        <input value="Search Trips by date" name="tripByDate" type="submit"> <br />
    </form>

<!-- <a href="${pageContext.request.contextPath}/createTrip"> Create trip </a> <br />

    <a href="${pageContext.request.contextPath}/deleteTrips"> Delete trip </a> <br />

    <a href="${pageContext.request.contextPath}/top5"> See Top 5 </a> <br />

    <a href="${pageContext.request.contextPath}/tripBetDate"> Search Trips between dates</a> <br />

    <a href="${pageContext.request.contextPath}/tripByDate"> Search Trips by date</a> <br />-->

</body>
</html>
