<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>


<html>
<head>
    <title>adminGoodManipulation</title>
</head>
<body>
<h1>Add a good: </h1>
<sf:form action="/goods/adminGoodsPage" method="post" modelAttribute="goodform">

    <sf:label path="name">Name of good: </sf:label> <sf:input path="name"/>
    <sf:label path="description">Description: </sf:label> <sf:input path="description"/>
    <sf:label path="category">Category: </sf:label> <sf:input path="category"/>
    <sf:label path="maxCount">Set a count of this good: </sf:label> <sf:input path="maxCount"/>
    <sf:label path="price">Set a price: </sf:label> <sf:input path="price"/>
    <c:forEach var="hub" items="${hubs}">

        <p><input name="hubId" type="radio" value="${hub.id}"> ${hub.address}. ${hub.city} </p>

    </c:forEach>


    <button type="submit">Сохранить</button>


</sf:form>

</body>
</html>
