<%--
  Created by IntelliJ IDEA.
  User: maria
  Date: 13/11/2021
  Time: 23:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sucesso</title>
</head>
<body>

<form action="${pageContext.request.contextPath}/logout" method="get">
    <input value="Sair" type="submit">
</form>

<form action="${pageContext.request.contextPath}/redirect" method="get">
    <input value="Voltar ao Menu" name="menu" type="submit">
</form>

<h1>Sucesso</h1>

<p> Operação falhou. Tente novamente </p>

<form action="${pageContext.request.contextPath}/redirect" method="get">
    <input value="Tentar novamente" name="buy" type="submit">
</form>

</body>
</html>
