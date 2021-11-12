<%--
  Created by IntelliJ IDEA.
  User: maria
  Date: 10/11/2021
  Time: 22:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Eliminar a conta</title>
</head>
<body>
    <h2>Tem a certeza que deseja eliminar a sua conta?</h2>

    <form action="${pageContext.request.contextPath}/delete" method="get">
        <input value="Eliminar" name="ok" type="submit">
        <input value="Cancelar" name="cancel" type="submit">
    </form>

</body>
</html>
