<%@page language="java" %>
<html>
<head>
    <link rel = "stylesheet" type="text/css" href="../style.css">
    <title>Home</title>
</head>
<style>
    .panel{
        height: 70%;
    }
    .panel .right-side input[type="submit"] {
        margin-top: 30%;
        width: 20%;
    }
</style>
<body>
<div class="panel">
    <div class="left-side">
        <img src="../gallery/home.png" alt="static/home.png">
    </div>
    <div class="right-side">
        <form action="/login" method="get">
            <h1>Welcome to the Inventory Management System</h1>
            <input type="submit" value="Login">
        </form>
    </div>
</div>
</body>

</html>