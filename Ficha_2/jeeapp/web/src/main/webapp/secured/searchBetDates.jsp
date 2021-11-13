<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Search between dates</title>
</head>
<body>
    <a href="${pageContext.request.contextPath}/secured/displayCM.jsp"> Menu </a> <br />
    <p>${auth}</p>

    <strong>Search between dates</strong>

    <form action="<%= request.getContextPath() %>/tripByDate" method="get">
        Date 1: <input name="date1" type="datetime-local"/> <br />
        Date 2: <input name="date2" type="datetime-local"/> <br />
        <input type="submit">
    </form>

</body>
</html>
