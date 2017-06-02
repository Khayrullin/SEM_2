<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Тест</title>
</head>


<body>

<a href="/goods/allGoods">Goods</a> <br>


<security:authorize access="hasAnyRole('ROLE_ADMIN')">

    <a href="/adminPage">User Administration</a> <br>
    <a href="/goods/adminGoodsPage">Goods Administration</a> <br>

</security:authorize>


<security:authorize access="isAnonymous()">

    <a href="/registration">Registration</a> <br>
    <a href="/login">Login</a> <br>

</security:authorize>

<security:authorize access="hasAnyRole('ROLE_BUYER', 'ROLE_ADMIN')">

    <a href="/goods/myOrders">My Orders</a> <br>

    <a href="/logout">Logout</a> <br>

</security:authorize>
</body>
</html>

