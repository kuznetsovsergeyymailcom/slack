
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Update user</title>
</head>
<body>
    <form action="/update" method="post">
        <table border="1">
            <thead>
                <tr>
                    <th>Id</th>
                    <th>Name</th>
                    <th>Password</th>
                    <th>Login</th>
                    <th></th>

                </tr>
            </thead>
            <tbody>
                <tr>
                    <input type="hidden" name="id" value="${user.id}">
                    <td><input type="text" placeholder="${user.id}" readonly></td>
                    <td><input type="text" name="name" placeholder="${user.name}"></td>
                    <td><input type="text" name="password" placeholder="${user.password}"></td>
                    <td><input type="text" name="login" placeholder="${user.login}"></td>
                    <td><input type="submit" value="Send"></td>
                </tr>
            </tbody>
        </table>
    </form>
</body>
</html>
