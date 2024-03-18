<%@page language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="<c:url value='../category.css' />" />
    <title>Categories</title>
</head>

<body>
<div class="content">
    <div class="addcategoryform">
        <h1>Add Category</h1>
        <form action="/addCategory" method="post">
            <input type="text" name="catName" id="catName" placeholder="Category Name" required>
            <div class="button-container">
                <button type="submit">Add</button>
                <button type="button" onclick="history.back()">Cancel</button>
            </div>
        </form>
    </div>
    <div class="tableview">
        <table class="categorydetails">
            <tr>
                <th>Category Id</th>
                <th>Category Name</th>
            </tr>
            <c:forEach var="categor" items="${category}">
                <tr>
                    <td>${categor.catId}</td>
                    <td>${categor.catName}</td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
</body>
</html>