<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Top 5</title>
</head>
<body>
    <a href="${pageContext.request.contextPath}/secured/displayCM.jsp"> Menu </a> <br />
    <p>${auth}</p>

    <strong>Top 5</strong><br>

    <c:choose>
        <c:when test="${empty top5}"> There is no top 5, we need to close the company </c:when>
        <c:otherwise>
            <c:forEach items="${top5}" var = "top">
                <c:out value="Client's name: ${top.name}">
                </c:out><br>
                <br>
            </c:forEach>
        </c:otherwise>
    </c:choose>

</body>
</html>
