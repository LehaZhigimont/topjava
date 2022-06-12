<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>User профиль</title>
    <style>
        dl {
            background: none repeat scroll 0 0 #FAFAFA;
            margin: 8px 0;
            padding: 0;
        }

        dt {
            display: inline-block;
            width: 170px;
        }

        dd {
            display: inline-block;
            margin-left: 8px;
            vertical-align: top;
        }
    </style>
</head>
<body>
<section>
    <h3><a href="index.html">Home</a></h3>
    <hr>
    <h2>${param.action == 'create' ? 'Create user' : 'Edit user'}</h2>
    <jsp:useBean id="user" type="ru.javawebinar.topjava.model.User" scope="request"/>
    <form method="post" action="users">
        <input type="hidden" name="id" value="${user.id}">
        <input type="hidden" name="enabled" value="${user.enabled}">
        <dl>
            <dt>Имя</dt>
            <dd><input type="name" value="${user.name}" name="name" required></dd>
        </dl>
        <dl>
            <dt>Почта</dt>
            <dd><input type="email" value="${user.email}" name="email" required></dd>
        </dl>
        <dl>
            <dt>Пароль</dt>
            <dd><input type="text" value="${user.password}" size=40 name="password" required></dd>
        </dl>
        <dl>
            <dt>Норма калорий в день</dt>
            <dd><input type="number" value="${user.caloriesPerDay}" name="calories" required></dd>
        </dl>
        <button type="submit">Save</button>
        <button onclick="window.history.back()" type="button">Cancel</button>
    </form>
</section>
</body>
</html>