<%--
  Created by IntelliJ IDEA.
  User: habar
  Date: 30.04.2017
  Time: 14:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>My Orders</title>
</head>
<body>
<p1>My Orders:</p1>


<table class="table table-bordered table-striped">
    <tr>
        <th>Goods</th>
        <th>Status</th>
    </tr>

    <c:forEach var="order" items="${orders}" varStatus="status">
        <tr>

            <td>
                <a href="/goods/order?id=${order.id}"> Order #${status.index}</a>
            </td>

        <td>
                ${order.status}
        </td>


        </tr>

    </c:forEach>


</table>

</body>
</html>
