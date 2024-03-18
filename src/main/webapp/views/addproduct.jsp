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
        <h1>Add Product</h1>
        <form action="/addproduct" method="post">
            <input type="text" name="proName" id="proName" placeholder="Product Name" required>
            <input type="number" name="quantity" id="quantity" placeholder="Product Quantity">
            <input type="number" name="price" id="price" placeholder="Product Price">
            <select id="category" name="category">
                <option value = "" disabled selected>Select Category</option>
                <c:forEach var="cat" items="${category}">
                    <option value="${cat.catName}" name="category"> ${cat.catName}</option>
                </c:forEach>
            </select>

            <div class="button-container">
                <button type="submit">Add</button>
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