<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html lang="ru">
<head>
    <title>Users</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h2>Users</h2>
<a href="users?action=create">New User</a>
<br><br>
<table border="1" cellpadding="8" cellspacing="0">
    <thead>
    <tr>
        <th>Имя</th>
        <th>Почта</th>
        <th>Роли</th>
        <th>Активный</th>
        <th>Зарегистрирован</th>
        <th></th>
        <th></th>
    </tr>
    </thead>
    <jsp:useBean id="users" scope="request" type="java.util.List"/>
    <c:forEach items="${users}" var="user">
        <tr>
            <td>${user.name}</td>
            <td>${user.email}</td>
            <td>
                <c:forEach items="${user.roles}" var="role">
                    ${role};
                </c:forEach>
            </td>
            <td>
                <input type="checkbox" ${user.enabled ? 'checked' : 'unchecked'}
            </td>
            <td>
                <fmt:formatDate value="${user.registered}" pattern="yyyy-MM-dd"/>
            </td>
            <td><a href="users?action=update&id=${user.id}">Update</a></td>
            <td><a href="users?action=delete&id=${user.id}">Delete</a></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>