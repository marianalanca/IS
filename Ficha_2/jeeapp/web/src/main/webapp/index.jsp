<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Title</title>
</head>
<body>
<h1>APP MANHOSA</h1>

<br />

<h2>Login</h2>

<form action="${pageContext.request.contextPath}/login" method="post">
    <input type="radio" required="" name="userType" value="user"/> Utilizador
    <input type="radio" name="userType" value="cm"/> Gestor <br />
    Email: <input name="email" type="email" placeholder="email..." />
    Password: <input name="key" type="password" placeholder="password..." />
    <input type="submit">
</form>
<br />

<form action="${pageContext.request.contextPath}/redirect" method="get">
    Não tem conta de utilizador?
    <input value="Criar conta" name="register" type="submit">  <br />
</form>


</body>
</html>