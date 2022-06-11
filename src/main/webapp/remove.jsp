<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <link rel="stylesheet" href="style.css" type="text/css"/>
    <title>Remove meal</title>
</head>
<body>
<div>
    <div>
        <h2>Remove meal</h2>
    </div>
    <table>
        <thead>
        <tr>
            <td>Date</td>
            <td>Description</td>
            <td>Calories</td>
        </tr>
        </thead>
        <jsp:useBean id="meal" scope="request" type="ru.javawebinar.topjava.model.MealTo"/>
            <tr style="color:${ (meal.excess ? 'red' : 'green')}">
                <th>${meal.dateTime.format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"))}</th>
                <th>${meal.description}</th>
                <th>${meal.calories}</th>
            </tr>
    </table>
    <div>
        <form method="post" name="meal" action="remove?action=submit">
            <p><input class="submitField" type="submit" name="btn" value="Remove"/>
                <input class="submitField" type="submit" name="btn" value="Cancel"/></p>
        </form>
    </div>
</div>
</body>
</html>