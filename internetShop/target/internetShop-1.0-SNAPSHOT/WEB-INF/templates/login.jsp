<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: root
  Date: 22.10.15
  Time: 17:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<h1>Login:</h1>
<c:if test="${not empty error}">
    Login error.
    Reason: ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
</c:if>
<form action="/login/process" method="post">
   Username : <input type="text" name="login"> <br>
   Password : <input type="password" name="password"> <br>
    <input type="submit">
</form>
</body>
</html>
