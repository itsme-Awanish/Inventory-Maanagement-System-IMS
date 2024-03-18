<%@page language="java" %>
<html>
<head>
    <link rel = "stylesheet" type="text/css" href="../style.css">
    <title>Login</title>
</head>
<body>
<div class="panel">
    <div class="left-side">
        <img src="gallery/inventory_login.jpg" alt="gallery/inventory_login.jpg">
    </div>

    <div class = "right-side">
        <h2> Login </h2>
        <form action="/actionDashboard" method="post">
            <label for="uname">User Name</label>
            <input type="text" id="uname" name="uname" autocomplete="off"><br>
            <label for="upass">User Password</label>
            <input type="password" id="upass" name="upass"><br>
            <input type="submit" value="Login">
        </form>
        <h3>${response}</h3>
    </div>
</div>

</body>
</html>