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

    <a href="${pageContext.request.contextPath}/index.jsp"> Log out </a> <br />

    <p>Welcome ${auth}</p>

    <h1>Menu</h1>

    <a href="${pageContext.request.contextPath}/registration.jsp"> Wallet </a>. <br />

    <a href="${pageContext.request.contextPath}/registration.jsp"> Consultar viagens </a>. <br />

    <a href="${pageContext.request.contextPath}/registration.jsp"> As suas viagens </a>. <br />

    <a href="${pageContext.request.contextPath}/secured/definitions.jsp"> Definições </a>. <br />

</body>
</html>