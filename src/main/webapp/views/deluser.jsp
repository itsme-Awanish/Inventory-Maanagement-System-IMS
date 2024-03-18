<%@page language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css"/>
    <link rel="stylesheet" href="<c:url value='../user.css'/>" />
    <title>Delete Users</title>
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
        .adduserform h1{
            margin: 0;
        }
    </style>
</head>

<body>
<div class="content">
    <div class="adduserform">
        <h1>Delete User</h1>
        <form action="/deleteuser" method="post">
            <p class="mandatory-note">
                Fill in the User Id of the user you want to Delete.
            </p>
            <input
                    type="number"
                    value="${singleUser.getUid()}"
                    name="uid"
                    pattern="[0-9]+"
                    placeholder="User Id"
                    required
            /><br />
            <p class="mandatory-note">${result}</p>
            <div class="button-container">
                <button type="submit" ${(isDisabled)?"disabled":""}>Delete</button>
                <button type="button" onclick="history.back()">Cancel</button>
            </div>
        </form>
    </div>
    <div class="tableview">
        <table class="userdetails">
            <tr>
                <th>Name</th>
                <th>Username</th>
                <th>Password</th>
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
