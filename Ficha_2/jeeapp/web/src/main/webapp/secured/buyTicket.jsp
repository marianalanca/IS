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
<html>
<head>
    <title>Resultado da pesquisa</title>
</head>
<body>


    <form action="${pageContext.request.contextPath}/logout" method="get">
        <input value="Sair" type="submit">
    </form>

    <form action="${pageContext.request.contextPath}/redirect" method="get">
        <input value="Voltar ao Menu" name="menu" type="submit">
        <input value="Voltar à Pesquisa" name="backSearchTrip" type="submit">
    </form>

    <h1>Viagens</h1>

        <!--
        private LocalDateTime departure_date;
        private String departure_point, destination;
        private int capacity;
        private double price;
        -->

    <p>Tem ${wallet}€ na sua carteira. </p>

    <br />


    <form action="${pageContext.request.contextPath}/purchase" method="post">
        <jsp:useBean id="tripSearch" scope="request" type="java.util.List"/>
        <c:choose>
            <c:when test="${empty tripSearch}">Não existem viagens disponíveis </c:when>
            <c:otherwise>
                <c:forEach var="item" items="${tripSearch}">
                    <hr />
                    <input type="radio" name="id" value=${item.getId()}/><strong>${item.getDeparture_point()} -> ${item.getDestination()}</strong> <br />
                    <strong>Data de partida:</strong> ${item.getDeparture_date_String()} <br />
                    <strong>Preço:</strong> ${item.getPrice()} <br />
                    <strong>Número de lugares:</strong> ${item.getCapacity()} <br />
                </c:forEach>

                <input name="seat" required="" type="number" step="0.01" placeholder="Selecionar lugar..." /> <br />

                <input value="Comprar" type="submit">
            </c:otherwise>
        </c:choose>
    </form>

</body>
</html>
