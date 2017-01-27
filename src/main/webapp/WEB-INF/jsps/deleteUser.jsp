<%--
  Created by IntelliJ IDEA.
  User: lukas
  Date: 1/23/17
  Time: 4:41 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Delete User</title>
    <%--<link rel="stylesheet" href="/fake.css"/>--%>
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <!-- Optional theme -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
          integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
</head>
<body>
<div class="form-group">
    Do you really want to delete ${requestScope.username} ?
    <br>

    <a type="button" class="btn btn-default" href="search">Cancel</a>
    <br>
    <br>
    <form action="delete_user" method="POST">

        <button type="submit" class="btn btn-default">Delete</button>

    </form>

</div>
</body>
</html>
