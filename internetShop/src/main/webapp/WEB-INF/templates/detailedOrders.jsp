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
    <title>Order</title>
</head>
<body>


<table class="table table-bordered table-striped">
    <tr>
        <th>Good name</th>
        <th>Count</th>
    </tr>

    <c:forEach var="order" items="${orders}" varStatus="status">
        <tr>

            <td>
                ${order.good.name}
            </td>

        <td>
                ${order.count}
        </td>


        </tr>

    </c:forEach>


</table>

</body>
</html>
