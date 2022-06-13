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
<h3><a href="index.html">Home</a></h3>
<hr/>
<h2>Meals</h2>
<a href="meals?action=create">Add Meal</a>


<form id="filter">
    <div>
        <div>
            <label for="startDate">От даты (включая)</label>
            <input type="date" name="startDate" id="startDate" autocomplete="off">
        </div>
        <div>
            <label for="endDate">До даты (включая)</label>
            <input type="date" name="endDate" id="endDate" autocomplete="off">
        </div>
        <div>
            <label for="startTime">От времени (включая)</label>
            <input type="time" name="startTime" id="startTime" autocomplete="off">
        </div>
        <div>
            <label for="endTime">До времени (исключая)</label>
            <input type="time" name="endTime" id="endTime" autocomplete="off">
        </div>
    </div>
    <div align="center">
        <button>
            Отменить
        </button>
        <button>
            Отфильтровать
        </button>
    </div>
</form>

<table border="1" cellpadding="8" cellspacing="0">
    <thead>
    <tr>
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