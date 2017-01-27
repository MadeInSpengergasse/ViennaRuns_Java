<%--
  Created by IntelliJ IDEA.
  User: lukas
  Date: 1/16/17
  Time: 3:52 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Create User</title>
    <%--<link rel="stylesheet" href="/fake.css"/>--%>
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <!-- Optional theme -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
          integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">

</head>
<body>
<form class="form-horizontal" action="create_user" method="POST">
    <div class="form-group">
<h1>Create a new User</h1>
    </div>
<table class="table table-striped">
    <tr>
        <td>Username:</td>
        <td><input type="text" name="username" /></td>
    </tr>
    <tr>
        <td>Password:</td>
        <td><input type="password" name="password" /></td>
    </tr>
   </table>
    <button type="submit" class="btn btn-default">Create</button>

</form>

</body>
</html>