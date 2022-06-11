<%@ page contentType="text/html;charset=UTF-8" %>
<html lang="ru">
<head>
    <link rel="stylesheet" href="style.css" type="text/css"/>
    <title>Edit meal</title>
</head>
<body>
<div>
    <div>
        <h2>Edit meal</h2>
    </div>
    <div>
        <jsp:useBean id="meal" scope="request" type="ru.javawebinar.topjava.model.MealTo"/>
        <form method="post" name="meal" action="update?action=submit">
            <p><label for="day">DateTime:</label>
                <input type="datetime-local" id="day" name="datetime" value="${meal.dateTime}"/></p>
            <p><label class="check-label" for="desc">Description:</label>
                <input id="desc" name="description" value="${meal.description}"/></p>
            <p><label for="cal">Calories:</label>
                <input id="cal" name="calories" value="${meal.calories}"/></p>
            <p><input class="submitField" type="submit" name="save" value="Save"/>
                <input class="submitField" type="submit" name="cancel" value="Cancel"/></p>
        </form>
    </div>
</div>
</body>
</html>