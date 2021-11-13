<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Search by date</title>
</head>
<body>
    <a href="${pageContext.request.contextPath}/secured/displayCM.jsp"> Menu </a> <br />
    <p>${auth}</p>

    <strong>Search by date</strong>

    <form action="<%= request.getContextPath() %>/tripByDate" method="get">
        Date: <input required="" name="date1" type="datetime-local"/> <br />
        <input type="submit">
    </form>

</body>
</html>
