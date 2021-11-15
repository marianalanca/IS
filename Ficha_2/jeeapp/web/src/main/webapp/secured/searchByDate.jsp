<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Search by date</title>
</head>
<body>
    <a href="${pageContext.request.contextPath}/index.jsp"> Log out </a> <br />
    <br>
    <form action="${pageContext.request.contextPath}/redirect" method="get">
        <input value="Menu" name="CM" type="submit">
    </form>

    <p>${auth}</p>

    <h1>Search by date</h1>

    <form action="<%= request.getContextPath() %>/tripByDate" method="post">
        Date: <input required="" name="date1" type="datetime-local"/> <br />
        <input type="submit">
    </form>

</body>
</html>
