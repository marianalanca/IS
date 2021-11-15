<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Top 5</title>
</head>
<body>
    <a href="${pageContext.request.contextPath}/index.jsp"> Log out </a> <br />
    <br>
    <form action="${pageContext.request.contextPath}/redirect" method="get">
        <input value="Menu" name="CM" type="submit">
    </form>

    <p>${auth}</p>

    <h1>Top 5</h1><br>

    <c:choose>
        <c:when test="${empty top5}"> There is no top 5, we need to close the company </c:when>
        <c:otherwise>
            <c:forEach items="${top5}" var = "top">
                <c:out value="\tClient's name: ${top.name}">
                </c:out><br>
                <br>
            </c:forEach>
        </c:otherwise>
    </c:choose>

</body>
</html>
