<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://topjava.javawebinar.ru/functions" %>
<html>
<head>
    <title>Meal list</title>
    <style>
        .normal {
            color: green;
        }

        .excess {
            color: red;
        }

        div {
            display: inline-block;
            margin-left: 150px;
            margin-top: 0;
            margin-bottom: 0;
        }
    </style>
</head>
<body>
<div>
    <div>
        <h3><a href="index.html">Home</a></h3>
    </div>
    <div>
        <form method="post" action="users?action=logout">
            <button type="submit">Logout</button>
        </form>
    </div>
    <hr/>
</div>
<h2>Meals</h2>
<a href="meals?action=create">Add Meal</a>
<table border="1" cellpadding="8" cellspacing="0">
    <thead>
    <tr>
        <th>id</th>
        <th>Date</th>
        <th>Description</th>
        <th>Calories</th>
        <th></th>
        <th></th>
    </tr>
    </thead>
    <c:forEach items="${requestScope.meals}" var="meal">
        <jsp:useBean id="meal" type="ru.javawebinar.topjava.to.MealTo"/>
        <tr class="${meal.excess ? 'excess' : 'normal'}">
            <td>${meal.id}</td>
            <td>
                    ${fn:formatDateTime(meal.dateTime)}
            </td>
            <td>${meal.description}</td>
            <td>${meal.calories}</td>
            <td><a href="meals?action=update&id=${meal.id}">Update</a></td>
            <td><a href="meals?action=delete&id=${meal.id}">Delete</a></td>
        </tr>
    </c:forEach>
</table>
<br><br>
</body>
</html>