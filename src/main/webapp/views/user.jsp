<%@page language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link
            rel="stylesheet"
            href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css"
    />
    <link rel="stylesheet" type="text/css" href="<c:url value='../user.css'/>" />
    <title>Manage Users</title>

</head>

<body>
<div class="content">
    <div class="adduserform">
        <h1>Manage User</h1>
        <form action="/actionUser" method="post">
            <input type="submit" name="action" value="Add User">
            <input type="submit" name="action" value="Update User">
            <input type="submit" name="action" value="Delete User">
            <input type="submit" name="action" value="Go to Dashboard">
        </form>
    </div>
    <div class="tableview">
        <table class="userdetails">
            <tr>
                <th>User Id</th>
                <th>Name</th>
                <th>User Name</th>
            </tr>
            <c:forEach var="user" items="${users}">
                <tr>
                    <td>${user.uid}</td>
                    <td>${user.name}</td>
                    <td>${user.uname}</td>
                    <!-- Add more data fields as needed -->
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
</body>
</html>