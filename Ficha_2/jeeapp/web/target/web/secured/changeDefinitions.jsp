<%--
  Created by IntelliJ IDEA.
  User: maria
  Date: 10/11/2021
  Time: 23:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Alterar definições de perfil</title>
</head>
<body>

    <a href="${pageContext.request.contextPath}/index.jsp"> Log out </a> <br />

    <h1>Alterar definições de perfil</h1>

    <form action="definitions" method="get">
        Novo nome: <input name="name" type="text" placeholder="username..." /> <br />
        Novo number of Citizen Card: <input name="cc" type="text" placeholder="cc..." /> <br />
        Novo endereço: <input name="address" type="text" placeholder="address..." /> <br />
        Nova Birth date: <input name="birthday" type="date"/> <br />
        Nova Password: <input name="password" type="password" placeholder="password..." /> <br />
        <input type="submit">
    </form>

</body>
</html>
