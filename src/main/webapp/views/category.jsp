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
        <h1>Manage Category</h1>
        <form action="/actionCategory" method="post">
            <input type="submit" name="action" value="Add Category">
            <input type="submit" name="action" value="Update Category">
            <input type="submit" name="action" value="Delete Category">
            <input type="submit" name="action" value="Go Back">
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
<c:set var="result" value="${message}"/>
<script type="text/javascript">
    var msg = "${result}";
    if(msg!=null && msg != ""){
        alert(msg);
    }else{

    }
</script>
</body>
</html>