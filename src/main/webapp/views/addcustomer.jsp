<%@page language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="<c:url value='../customer.css' />" />
    <title>Customers</title>
</head>

<body>
    <div class="content">
        <div class="addcustomerform">
            <h1>Add Customer</h1>
            <form action="/addcustomer" method="post">
                <input type="text" name="cName" id="cName" placeholder="Customer Name" required>
                <input type="number" name="phone_no" id="phone_no" placeholder="Phone Number" required>
                <div class="button-container">
                    <button type="submit">Add</button>
                </div>
            </form>
        </div>
    </div>
</body>
</html>