<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Goods</title>
</head>
<body>

<sf:form action="/goods/search" method="get">
    <input name="name" type="text" size="40">
    <button type="submit">Search</button>
</sf:form>
<c:set var="error" scope="session" value="${error}"/>
<c:if test="${error == true}">
    <p1>Item not Found</p1>
</c:if>


<c:forEach var="category" items="${categories}" varStatus="status">

    <a href="/goods/category?name=${category}">${category}</a>

</c:forEach>
<br>


<c:if test="${error == null|| error == false}">

    <sf:form action="/goods/addGoodToCart" method="post" modelAttribute="goodForm">
        <c:forEach var="good" items="${goodForm.goods}" varStatus="status">
            <form:input type="hidden" path="goods[${status.index}].id"/>

            <c:set var="count" scope="session" value="${goodsOnHub[status.index].allCount}"/>

            <c:if test="${count > 0}">
                <form:checkbox path="goods[${status.index}].added" value="${good.added}"/>

            </c:if>
            <c:if test="${count == 0}">
                <form:checkbox path="goods[${status.index}].added" disabled="true" value="${good.added}"/>
                NO GOOD AVAILABLE ON HUB: </c:if>

            <a href="/goods/goodMainPage?goodId=${good.id}">${good.name}</a>
            <p1> - ${good.price} rubles</p1>
            <br>

        </c:forEach>

        <button type="submit">Add to Cart</button>
    </sf:form>

</c:if>
</body>
</html>

