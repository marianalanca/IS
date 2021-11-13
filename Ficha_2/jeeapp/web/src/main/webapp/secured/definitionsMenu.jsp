<%--
  Created by IntelliJ IDEA.
  User: maria
  Date: 12/11/2021
  Time: 16:15
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

<a href="${pageContext.request.contextPath}/secured/display.jsp"> Voltar</a> <br />

<h1>Definições de utilizador</h1>

<form action="${pageContext.request.contextPath}/redirect" method="get">
    <input value="Editar o seu perfil" name="edit" type="submit">  <br />
    <input value="Eliminar perfil" name="delete" type="submit">  <br />
</form>

</body>
</html>

