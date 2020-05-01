<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Registration</title>
    <style><%@include file="/WEB-INF/source/css/login.css"%></style>
</head>
<body>
<form class="box" action="${pageContext.request.contextPath}/" method="post">
    <h1>Register</h1>
    <input type="text" name="login" placeholder="Username">
    <input type="password" name="pass" placeholder="Password">
    <input type="submit" name="" value="Register">
</form>
</body>
</html>