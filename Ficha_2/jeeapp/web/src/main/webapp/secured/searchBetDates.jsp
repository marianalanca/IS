<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Search between dates</title>
</head>
<body>
    <a href="${pageContext.request.contextPath}/index.jsp"> Log out </a> <br />

    <form action="${pageContext.request.contextPath}/redirect" method="get">
        <input value="Menu" name="CM" type="submit">
    </form> <br>

    <p>${auth}</p>

    <h1>Search between dates</h1>

    <form action="<%= request.getContextPath() %>/tripBetDate" method="post">
        Date 1: <input required="" name="date1" type="datetime-local"/> <br />
        Date 2: <input required="" name="date2" type="datetime-local"/> <br />
        <input type="submit">
    </form>

</body>
</html>
