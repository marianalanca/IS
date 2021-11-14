
<%--
  Created by IntelliJ IDEA.
  User: maria
  Date: 31/10/2021
  Time: 15:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Title</title>
</head>
<body>

    <form action="${pageContext.request.contextPath}/logout" method="get">
        <input value="Sair" type="submit">
    </form>

    <p>Welcome ${auth}</p>

    <h1>Menu</h1>

    <form action="${pageContext.request.contextPath}/redirect" method="get">
        <input value="Carregar carteira" name="wallet" type="submit">  <br />
        <input value="Consultar viagens disponíveis/Comprar" name="buy" type="submit">  <br />
        <input value="Cancelar reservas" name="refund" type="submit"> <br />
        <input value="Editar perfil" name="profile" type="submit"> <br />
        <input value="CM" name="CM" type="submit">
    </form>

</body>
</html>
