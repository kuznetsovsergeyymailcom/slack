
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Update user</title>
</head>
<body>
    <form action="/admin/update" method="post">
        <table border="1">
            <thead>
                <tr>
                    <th>Id</th>
                    <th>Name</th>
                    <th>Password</th>
                    <th>Login</th>
                    <th>Role</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <input type="hidden" name="id" value="${editUser.id}">
                    <td><input type="text" placeholder="${editUser.id}" readonly></td>
                    <td><input type="text" name="name" placeholder="${editUser.name}"></td>
                    <td><input type="text" name="password" placeholder="${editUser.password}"></td>
                    <td><input type="text" name="login" placeholder="${editUser.login}"></td>
                    <td><select name="role">
                        <option value="user" selected>user</option>
                        <option value="admin">admin</option></select>
                    </td>
                    <td><input type="submit" value="Send"></td>
                </tr>
            </tbody>
        </table>
    </form>
</body>
</html>
