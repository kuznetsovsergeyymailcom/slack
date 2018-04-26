
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User page</title>
</head>
<body>
    User main page
    <table border="2" cellpadding="2" cellspacing="2">
        <thead>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Password</th>
            <th>Login</th>
            <th>Role</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td>${user.id}</td>
            <td>${user.name}</td>
            <td>${user.password}</td>
            <td>${user.login}</td>
            <td>${user.roles}</td>
        </tr>
        </tbody>
    </table>
    <br/>
    <a href="/logout">Logout</a>
    <br/>
    <p style="color: red">${message}</p>
</body>
</html>
