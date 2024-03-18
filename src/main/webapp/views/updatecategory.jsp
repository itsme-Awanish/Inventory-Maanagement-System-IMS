<%@page language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="<c:url value='../category.css' />" />
    <title>Categories</title>
    <style>


        .mandatory-note{
            font-style: italic;
            font-weight: lighter;
            color: black;
            margin-bottom: 5px;
        }

        /* Custom style to remove arrow from number input */
        input[type="number"]::-webkit-inner-spin-button,
        input[type="number"]::-webkit-outer-spin-button {
            -webkit-appearance: none;
            margin: 0;
        }

        /* Custom style for validation message */
        input:invalid {
            border-color: red;
        }

        input:invalid:required {
            border-color: red;
        }

        input:invalid:required:valid {
            border-color: initial;
        }

        input:invalid:required:valid+span::before {
            content: "Please fill out this field.";
            color: red;
            font-size: 12px;
        }
        .button-container1 button {
            font-size: 14px;
            padding: 5px 10px;
        }
        .addcategoryform h1{
            margin: 0;
        }
    </style>
</head>

<body>
<div class="content">
    <div class="addcategoryform">
        <h1>Update Category</h1>
        <form action="/updateCategory" method="post">
            <p class="mandatory-note">Give the category id you want to update</p>
            <input type="number" name="catId" placeholder="Category Id" required>
            <input type="text" name="catName" id="catName" placeholder="Category Name" required>
            <div class="button-container">
                <button type="submit">Update</button>
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