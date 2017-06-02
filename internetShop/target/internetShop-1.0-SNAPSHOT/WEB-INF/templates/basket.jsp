<%--
  Created by IntelliJ IDEA.
  User: habar
  Date: 30.04.2017
  Time: 14:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Basket</title>
</head>
<body>
<h1>Basket:</h1>
<br>

<sf:form action="/goods/buy" method="post">
    <c:forEach var="order" items="${recruiting}" varStatus="status">


        <a href="/goods/goodMainPage?goodId=${order.good.id}">${order.good.name}</a></p1>
        <p1> - ${order.good.price} rubles</p1>
        <br>

        <div class="box">
            <label for="${order.good.id}"><abbr title="Quantity">Count</abbr></label>
            <input id="${order.good.id}"  value="${order.count}"/>
            <button type="button" id="down" onclick="doAjax(-1,${order.good.id})">-</button>
            <button type="button" id="up" onclick="doAjax(1,${order.good.id})">+</button>
        </div>
    </c:forEach>

    <button type="submit">Buy now!</button>
</sf:form>

<script src="webjars/jquery/1.11.1/jquery.js"></script>
<script src="webjars/jquery-ui/1.11.3/jquery-ui.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.3/i18n/jquery-ui-i18n.min.js"></script>
<script src="webjars/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.15.1/jquery.validate.js"></script>
<script>

    function modify_qty(val, status, maxCount) {
        var qty = document.getElementById(status).value;
        var new_qty = parseInt(qty, 10) + val;
        document.getElementById(status).setAttribute("style", "background-color:white")
        if (new_qty < 0) {
            new_qty = 0;
        }
        if (new_qty > parseInt(maxCount)) {
            new_qty = parseInt(maxCount);
            document.getElementById(status).setAttribute("style", "background-color:red");
        }
        document.getElementById(status).value = new_qty;
        return new_qty;
    }


    function doAjax(val, id) {
        var count = document.getElementById(id).value ;
        count = parseInt(count) + parseInt(val);

        $.ajax({
            type: 'GET',
            url: "/goods/ajax",
            data: {
                id: id,
                count : count
            },
            success: [function (data) {
                modify_qty(val, id, data);
            }]
        })
    }


</script>
</body>

</html>
