
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <form action="/add" method="post">
        <table border="2">
            <thead>
            <tr>
                <th>Name</th>
                <th>Password</th>
                <th>Login</th>
            </tr>
            </thead>
                <tr>
                    <td><input type="text" name="name"></td>
                    <td><input type="text" name="password"></td>
                    <td><input type="text" name="login"></td>
                    <td><input type="submit" value="Send"></td>
                </tr>
        </table>
    </form>
</body>
</html>
