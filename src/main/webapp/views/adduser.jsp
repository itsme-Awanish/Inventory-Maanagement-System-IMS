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
    <title>Add Users</title>

</head>

<body>
<div class="content">
    <div class="adduserform">
        <h1>ADD User</h1>
        <form action="/adduser" method="post">
            <input type="text" name="name" placeholder="Name" /><br />
            <input type="text" name="uname" placeholder="User name" /><br />
            <input type="password" name="upass" placeholder="Password" /><br />
            <div class="button-container">
                <button type="submit">Add</button>
                <button type="button" onclick="history.back()">Cancel</button>
            </div>
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