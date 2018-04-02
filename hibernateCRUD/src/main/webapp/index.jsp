<%--
  Created by IntelliJ IDEA.
  User: ssku
  Date: 29.03.2018
  Time: 16:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>login page</title>
</head>
<body>
<form method="post" action="/login">
    <table>
        <thead border="2">
        <tr>
            <th>Login</th>
            <th>Password</th>
        </tr>
        </thead>
        <tbody>
            <tr>
                <td><input type="text" name="name"></td>
                <td><input type="password" name="password"></td>
            </tr>
        </tbody>
    </table>
    <br/>
    <input type="submit" value="Send">
</form>
</body>
</html>
