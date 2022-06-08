<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<html lang="ru">
<head>
    <title>Meals</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h2>Meals</h2>
<h4><a href="add.html">Add meal</a></h4>
<br>
<table border="1" align="100" cellpadding="10" border-width:="80%">
    <thead>
    <tr>
        <th>Date</th>
        <th>Description</th>
        <th>Calories</th>
        <th>Update</th>
        <th>Delete</th>
    </tr>
    </thead>
    <jsp:useBean id="mealsTo" scope="request" type="java.util.List"/>
    <c:forEach items="${mealsTo}" var="meal">
        <tr style="color:${ (meal.excess == true ? 'red' : 'green')}">
            <th>${meal.dateTime.format( DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"))}</th>
            <th>${meal.description}</th>
            <th>${meal.calories}</th>
            <th><a href="update">Update</a></th>
            <th><a href="remove">Delete</a></th>
        </tr>
    </c:forEach>
</table>
</body>
</html>