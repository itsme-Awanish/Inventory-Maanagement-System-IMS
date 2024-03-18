<%@page language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="<c:url value='../product.css' />" />
    <title>Products</title>
</head>

<body>
<div class="content">
    <div class="addproductform">
        <h1>Update Product</h1>
        <form action="/updateproduct" method="post">
            <p><i> Give the product id you want to update</i></p>
            <input type="number" name="proId" placeholder="Product Id" required>
            <input type="number" name="quantity" id="quantity" placeholder="Product Quantity" required>
            <input type="number" name="proPrice" id="proPrice" placeholder="Product Price" required>
            <div class="button-container">
                <button type="submit">Update</button>
                <button type="button" onclick="history.back()">Cancel</button>
            </div>
        </form>
    </div>
    <div class="tableview">
        <table class="productdetails">
            <tr>
                <th>Product Id</th>
                <th>Product Name</th>
                <th>Product Quantity</th>
                <th>Product Price</th>
                <th>Product Category</th>
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