<%--
  Created by IntelliJ IDEA.
  User: maria
  Date: 12/11/2021
  Time: 21:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Consultar viagens</title>
</head>
<body>

    <form action="${pageContext.request.contextPath}/logout" method="get">
        <input value="Sair" type="submit">
    </form>

    <form action="${pageContext.request.contextPath}/redirect" method="get">
        <input value="Voltar ao Menu" name="menu" type="submit">
    </form> <br />

    <h1>Procurar viagens</h1>

    <form action="${pageContext.request.contextPath}/searchTrip" method="post">
        Início intervalo: <input name="beg_date" required="" type="datetime-local"/> <br />
        Fim intervalo: <input name="end_date" required="" type="datetime-local"/> <br />
        <input value="Procurar" type="submit">
    </form>

</body>
</html>
