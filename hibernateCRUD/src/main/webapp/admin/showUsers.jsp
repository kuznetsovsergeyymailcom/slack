<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Users</title>
</head>
<body>
    <table border="2">
        <thead>
            <tr>
                <th>Id</th>
                <th>Name</th>
                <th>Password</th>
                <th>Login</th>
                <th>Role</th>
                <th>Edit</th>
                <th>Remove</th>
            </tr>
        </thead>
        <tbody>
        <c:forEach items="${users}" var="user">
            <tr>
                <td>${user.id}</td>
                <td>${user.name}</td>
                <td>${user.password}</td>
                <td>${user.login}</td>
                <td>${user.roles}</td>
                <td align="center"><a href="/admin/update?id=${user.id}">Update</a></td>
                <td align="center"><a href="/admin/remove?id=${user.id}">Remove</a></td>
            </tr>
        </c:forEach>

        </tbody>

    </table>
    <a href="/admin/add">Add</a>
    <br/>
    <a href="/logout">Logout</a>
    <br/>
    <p style="color: red">${message}</p>
</body>
</html>
