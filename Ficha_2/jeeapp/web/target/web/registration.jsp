<%--
  Created by IntelliJ IDEA.
  User: maria
  Date: 10/11/2021
  Time: 10:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="message" scope="request" type="java.lang.String"/>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Register</title>
</head>
<body>

<form action="${pageContext.request.contextPath}/redirect" method="get">
<input value="Voltar" name="home" type="submit"> <br />
</form>

    <strong>Registration</strong>

    <form action="${pageContext.request.contextPath}/register" method="post">
        Name: <input required="" name="name" type="text" placeholder="username..." /> <br />
        Email:<input required="" name="email" type="email" placeholder="email..." /> <br />
        Number of Citizen Card: <input required="" name="cc" type="text" placeholder="cc..." /> <br />
        Address: <input required="" name="address" type="text" placeholder="address..." /> <br />
        Password: <input required="" name="password" type="password" placeholder="password..." /> <br />
        Confirm password: <input required="" name="confirmation" type="password" placeholder="password..." /> <br />
        <input type="submit">
    </form>

    ${message}

</body>
</html>
