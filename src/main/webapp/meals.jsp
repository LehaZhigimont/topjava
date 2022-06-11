<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="java.time.format.DateTimeFormatter" %>
<html lang="ru">
<head>
    <link rel="stylesheet" href="style.css" type="text/css"/>
    <title>Meals</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h2>Meals</h2>
<h4><a href="add.jsp">Add meal</a></h4>
<br>
<table>
    <thead>
    <tr>
        <td>Date</td>
        <td>Description</td>
        <td>Calories</td>
        <td></td>
        <td></td>
    </tr>
    </thead>
    <jsp:useBean id="mealsTo" scope="request" type="java.util.List"/>
    <c:forEach items="${mealsTo}" var="meal">
        <tr style="color:${ (meal.excess ? 'red' : 'green')}">
            <th>${meal.dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))}</th>
            <th>${meal.description}</th>
            <th>${meal.calories}</th>
            <th><a href="update">Update</a></th>
            <th><a href="remove">Delete</a></th>
        </tr>
    </c:forEach>
</table>
</body>
</html>