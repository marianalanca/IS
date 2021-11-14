<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.List" %>
<%--
  Created by IntelliJ IDEA.
  User: maria
  Date: 12/11/2021
  Time: 22:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="tickets" scope="request" type="java.util.List"/>
<html>
<head>
    <title>Refund</title>
</head>
<body>


<form action="${pageContext.request.contextPath}/logout" method="get">
    <input value="Sair" type="submit">
</form>

<form action="${pageContext.request.contextPath}/redirect" method="get">
    <input value="Voltar ao Menu" name="menu" type="submit">
</form> <br />

<h1>Pedir reembolso</h1>

<br />

<form action="${pageContext.request.contextPath}/refund" method="post">
    <c:choose>
        <c:when test="${empty tickets}">Não existem viagens disponíveis </c:when>
        <c:otherwise>
            <c:forEach var="item" items="${tickets}">
                <hr />
                <input type="radio" name="id" value=${item.getId()}/><strong>${item.getTrip().getDeparture_point()} -> ${item.getTrip().getDestination()}</strong> <br />
                <strong>Data de partida:</strong> ${item.getTrip().getDeparture_date_String()} <br />
                <strong>Lugar:</strong> ${item.getSeat()} <br />
            </c:forEach>

            <hr />

            <input value="Reembolsar" type="submit">

        </c:otherwise>
    </c:choose>
</form>


</body>
</html>
