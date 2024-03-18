<%@page language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="<c:url value='../product.css' />" />
    <title>Productss</title>
</head>

<body>
<div class="content">
    <div class="addproductform">
        <h1>Manage Product</h1>
        <form action="/actionProduct" method="post">
            <input type="submit" name="action" value="Add Product">
            <input type="submit" name="action" value="Update Product">
            <input type="submit" name="action" value="Delete Product">
            <input type="submit" name="action" value="Go Back">
        </form>
    </div>
    <div class="tableview">
        <table class="productdetails">
            <tr>
                <th>Product Id</th>
                <th>Product Name</th>
                <th>Product Quantity</th>
                <th>Product Price</th>
                <th>Product Description</th>
            </tr>
            <c:forEach var="prod" items="${product}">
                <tr>
                    <td>${prod.proId}</td>
                    <td>${prod.proName}</td>
                    <td>${prod.quantity}</td>
                    <td>${prod.price}</td>
                    <td>${prod.category}</td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
</body>
</html>