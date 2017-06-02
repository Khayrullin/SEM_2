<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>adminPage</title>
</head>
<body>
<h1>Admin Page:</h1>
<table class="table table-bordered table-striped">
    <tr>
        <th>Username</th>
        <th>Role</th>
        <th>Email</th>
        <th>Activation</th>
        <th>Delete</th>
        <th>Change</th>
    </tr>

    <c:forEach var="user" items="${users}">
        <sf:form action="/adminPage" method="post" modelAttribute="userAdministrationForm">
            <tr>

            <td>
                    ${user.username}

            </td>

            <td>
                <select name="role">
                    <option ${user.role == 'ROLE_BUYER' ? 'selected' : ''} value="ROLE_BUYER">ROLE_BUYER</option>
                    <option ${user.role == 'ROLE_ADMIN' ? 'selected' : ''} value="ROLE_ADMIN">ROLE_ADMIN</option>
                </select>
            </td>

            <td>${user.email}</td>

            <td>
                <select name="activation">
                    <option ${user.activation == 'True' ? 'selected' : ''} value="True">True</option>
                    <option ${user.activation == 'False' ? 'selected' : ''} value="False">False</option>
                </select>
            </td>

            <td>
                <select name="delete">
                    <option value="False">False</option>
                    <option value="True">True</option>
                </select>
            </td>


            <td>
                <button name="username" type="submit" value="${user.username}">Update</button>
            </td>


        </sf:form>
        </tr>
    </c:forEach>


</table>
<br>
<a href="/">Index</a> <br>

<script src="http://code.jquery.com/jquery-latest.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.15.1/jquery.validate.js"></script>

<script>


    $(document).ready(function () {

        $("#form").validate({

            rules: {

                email: {
                    required: true,
                    minlength: 20,
                    maxlength: 255
                }

            },

            messages: {

                email: {
                    required: "Это поле обязательно для заполнения",
                    minlength: "Логин должен быть минимум 20 символov",
                    maxlength: "Максимальное число символо - 255"
                }


            }

        });

    });

</script>
</body>
</html>
