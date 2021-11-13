<%--
  Created by IntelliJ IDEA.
  User: maria
  Date: 13/11/2021
  Time: 17:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Refund</title>
</head>
<body>
    <form action="${pageContext.request.contextPath}/logout" method="get">
        <input value="Sair" type="submit">
    </form>

    <a href="${pageContext.request.contextPath}/secured/display.jsp"> Voltar atrás</a> <br />

    <h1>Pedir reembolso</h1>

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

                <input value="Cancelar reserva" type="submit">
            </c:otherwise>
        </c:choose>
    </form>

</body>
</html>
