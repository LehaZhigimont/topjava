<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <link rel="stylesheet" href="style.css" type="text/css"/>
    <title>Edit meal</title>
</head>
<body>
<div>
    <div>
        <h2>Add meal</h2>
    </div>
    <div>
        <form method="post" name="meal" action="add?action=submit">
            <p><label for="day">DateTime:</label>
                <input type="datetime-local" id="day" name="datetime"/></p>
            <p><label class="check-label" for="desc">Description:</label>
                <input id="desc" name="description"/></p>
            <p><label for="cal">Calories:</label>
                <input id="cal" name="calories"/></p>
            <p><input class="submitField" type="submit" name="btn" value="Save"/>
                <input class="submitField" type="submit" name="btn" value="Cancel"/></p>
        </form>
    </div>
</div>
</body>
</html>