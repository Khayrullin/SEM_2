<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: root
  Date: 24.10.15
  Time: 16:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>

    <style>
        .error {
            color: #ff0000;
        }

        .errorblock {
            color: #000;
            background-color: #ffEEEE;
            border: 3px solid #ff0000;
            padding: 8px;
            margin: 16px;
        }
    </style>
</head>
<body>

<sf:form action="/registration" method="post" modelAttribute="userform">

    <sf:errors path="*" cssClass="errorblock" element="div" />

    <sf:label path="username">Login: </sf:label> <sf:input path="username"/>
    <sf:errors path="username" cssClass="error" />

    <sf:label path="email">Email: </sf:label> <sf:input path="email"/>
    <sf:errors path="email" cssClass="error" />

    <sf:label path="password">password: </sf:label> <sf:input path="password"/>
    <sf:errors path="password" cssClass="error" />

    <sf:label path="repassword">repassword: </sf:label> <sf:input path="repassword"/>
    <sf:errors path="repassword" cssClass="error" />

    <button type="submit">Сохранить</button>
</sf:form>
</body>
</html>
