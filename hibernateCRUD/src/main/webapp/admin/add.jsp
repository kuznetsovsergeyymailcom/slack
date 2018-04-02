
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <form action="/admin/add" method="post">
        <table border="2">
            <thead>
            <tr>
                <th>Name</th>
                <th>Password</th>
                <th>Login</th>
                <th>Role</th>
            </tr>
            </thead>
                <tr>
                    <td><input type="text" name="name"></td>
                    <td><input type="text" name="password"></td>
                    <td><input type="text" name="login"></td>
                    <td><select name="role">
                        <option value="user" selected>user</option>
                        <option value="admin">admin</option></select>
                    </td>
                    <td><input type="submit" value="Send"></td>
                </tr>
        </table>
    </form>
</body>
</html>
