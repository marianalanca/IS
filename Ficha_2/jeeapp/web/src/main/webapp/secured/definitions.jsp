<%--
  Created by IntelliJ IDEA.
  User: maria
  Date: 10/11/2021
  Time: 22:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Definições</title>
</head>
<body>

    <form action="${pageContext.request.contextPath}/logout" method="get">
        <input value="Sair" type="submit">
    </form>

    <h1>Definições</h1>

    <a href="${pageContext.request.contextPath}/secured/changeDefinitions.jsp"> Editar o seu perfil</a> <br />

    <a href="${pageContext.request.contextPath}/secured/deletionConfirmation.jsp"> Eliminar perfil</a>

</body>
</html>
