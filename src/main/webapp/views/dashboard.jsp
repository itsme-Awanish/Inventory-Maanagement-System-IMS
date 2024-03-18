<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link
            rel="stylesheet"
            href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css"
    />
    <link rel="stylesheet" href="../user.css" />
    <title>Users</title>
    <style>
        .adduserform h1{
            margin-bottom: -3%;
        }
    </style>
</head>

<body>
<div class="content">
    <div class="adduserform">
        <h1>Dashboard</h1>
        <form action="/actionmanage" method="post">
            <input type="submit" name="action" value="Manage Category">
            <input type="submit" name="action" value="Manage Product">
            <input type="submit" name="action" value="Place Order">
            <button type="submit" onclick="history.back()">Back</button>
        </form>
    </div>

    <div class="tableview">
        <table class="userdetails">
            <tr>
                <th>Customer Name</th>
                <th>Customer Contact</th>
                <th>Product Quantity</th>
                <th>Payment Method</th>
                <th>Order Date</th>
                <th>order time</th>
                <th>Amount</th>
            </tr>
        </table>
        <table class="userdetails">
            <tr>
                <th>inventory Size</th>
                <th>No of Products</th>
                <th>Stock overflow</th>
                <th>Today's Turnover</th>
                <th>Last month Turnover</th>
            </tr>
        </table>
    </div>
</div>
</body>
</html>