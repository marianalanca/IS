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
    <title>Carregar carteira</title>
</head>
<body>

<form action="${pageContext.request.contextPath}/logout" method="get">
    <input value="Sair" type="submit">
</form>

<h1>Carregar a carteira</h1>

<form action="${pageContext.request.contextPath}/wallet" method="post">
    Valor: <input name="value" required="" type="number" step="0.01" placeholder="Quantidade para carregar..." /> <br />
    <input type="submit">
</form>

</body>
</html>

