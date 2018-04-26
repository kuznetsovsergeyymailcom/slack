
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>

    <script type="text/javascript">
        function validateForm()
        {
            var a=document.forms["addUser"]["name"].value;
            var b=document.forms["addUser"]["password"].value;
            var c=document.forms["addUser"]["login"].value;
            if (a==null || a=="",b==null || b=="",c==null || c=="")
            {
                alert("Please Fill All Required Field");
                return false;
            }
        }
    </script>

</head>
<body>
    <form action="/admin/add" method="post" name="addUser" onsubmit="validateForm()">
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
                    <td><select name="roles", multiple="multiple">
                        <option value="user" selected>user</option>
                        <option value="admin">admin</option></select>
                    </td>
                    <td><input type="submit" value="Send"></td>
                </tr>
        </table>
    </form>
    <br/>
    <p style="color: red">${message}</p>
    <a href="/admin">Go to page with all users</a>
</body>
</html>
