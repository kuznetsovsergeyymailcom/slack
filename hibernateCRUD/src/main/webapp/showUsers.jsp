<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Users</title>
    <link rel="stylesheet" type="text/sources.css" href="/css/style.css">
</head>
<body>
    <table border="2">
        <thead>
            <tr>
                <th>id</th>
                <th>name</th>
                <th>password</th>
                <th>login</th>
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
                <td align="center"><a href="/update?id=${user.id}">Update</a></td>
                <td align="center"><a href="/remove?id=${user.id}">Remove</a></td>
            </tr>
        </c:forEach>

        </tbody>

    </table>
    <a href="/add">Add</a>

</body>
</html>
