<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Success</title>
</head>
<body>

<a href="${pageContext.request.contextPath}/index.jsp"> Log out </a> <br />

<form action="${pageContext.request.contextPath}/redirect" method="get">
    <input value="Menu" name="CM" type="submit">
</form>

<p>Welcome ${auth}</p>

<h1>Success</h1>

<p> Operation concluded with success. you may return to the Menu. </p>

</body>
</html>
