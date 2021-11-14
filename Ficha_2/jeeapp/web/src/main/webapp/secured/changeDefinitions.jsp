<%--
  Created by IntelliJ IDEA.
  User: maria
  Date: 10/11/2021
  Time: 23:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="message" scope="request" type="java.lang.String"/>
<html>
<head>
    <title>Alterar definições de perfil</title>
</head>
<body>

    <form action="${pageContext.request.contextPath}/logout" method="get">
        <input value="Sair" name="ok" type="submit">
    </form>

    <a href="${pageContext.request.contextPath}/secured/definitionsMenu.jsp"> Voltar</a> <br />

    <h1>Alterar definições de perfil</h1>

    <form action="${pageContext.request.contextPath}/definitions" method="post">
        Novo nome: <input name="name" type="text" placeholder="username..." /> <br />
        Novo number of Citizen Card: <input name="cc" type="text" placeholder="cc..." /> <br />
        Novo endereço: <input name="address" type="text" placeholder="address..." /> <br />
        Nova Password: <input name="password" type="password" placeholder="password..." /> <br />
        <input type="submit">
    </form>

    ${message} <br />

</body>
</html>
