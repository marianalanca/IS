<%--
  Created by IntelliJ IDEA.
  User: maria
  Date: 10/11/2021
  Time: 10:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Register</title>
</head>
<body>
    <strong>Registration</strong>

    <form action="register" method="post">
        Name: <input name="name" type="text" placeholder="username..." /> <br />
        Email:<input name="email" type="email" placeholder="email..." /> <br />
        Number of Citizen Card: <input name="cc" type="text" placeholder="cc..." /> <br />
        Address: <input name="address" type="text" placeholder="address..." /> <br />
        Birth date: <input name="birthday" type="date"/> <br />
        Password: <input name="password" type="password" placeholder="password..." /> <br />
        Confirm password: <input name="confirmation" type="password" placeholder="password..." /> <br />
        <input type="submit">
    </form>

</body>
</html>
