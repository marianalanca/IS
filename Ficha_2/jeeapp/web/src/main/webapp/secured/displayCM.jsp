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

    <a href="${pageContext.request.contextPath}/secured/createTrip.jsp"> Create trip </a> <br />

    <a href="${pageContext.request.contextPath}/deleteTrip"> Delete trip </a> <br />

    <a href="${pageContext.request.contextPath}/secured/top5.jsp"> See Top 5 </a> <br />

    <a href="${pageContext.request.contextPath}/secured/searchBetDates.jsp"> Search Trips between dates</a> <br />

    <a href="${pageContext.request.contextPath}/secured/searchByDate.jsp"> Search Trips by date</a> <br />

<!-- ist all passengers on a given trip listed during one of the previous searches -->
<!-- The system sends a daily summary of the revenues of that day’s trips to the managers. -->

</body>
</html>
