<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
    <title>Place Order</title>
    <link rel="stylesheet" href="../order.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css"/>
    <!-- Include jQuery -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>
<div class="content">
    <div class="sectionOne">
        <form action="/order/addToCart" method="post">

            <label for="phoneNumber">Phone Number:</label>
            <input type="number" min="0" autocomplete="off" oninput="limitInputLength(this)" value="${(Reciept.getPhone_no()!= null)?Reciept.getPhone_no():""}" id="phoneNumber" name="phoneNumber" ${(Reciept.isNOFilled())?"disabled":""} ${(Reciept.isNOFilled())?"":"required"} placeholder="${(Reciept.getPhone_no()!= null)?Reciept.getPhone_no():""}"/>

            <button type="submit" formaction="/order/findCustomer" ${(Reciept.isNOFilled())?"disabled":""}>
                <i class="fas fa-search"></i>
            </button><br/>
            <label for="customerName">Customer Name:</label>
            <input type="text" value="${Reciept.getCname()}" autocomplete="off" id="customerName" name="customerName"  ${(Reciept.isFilled())?"disabled":""} placeholder="${Reciept.getCname()}"/>

            <label for="categorySelect">Select Category:</label>
            <select id="categorySelect" name="selectedCategory">
                <option value = "" disabled selected>Select Category</option>
                <c:forEach items="${categories}" var="category">
                    <option value="${category.catName}">${category.catName}</option>
                </c:forEach>
            </select>

            <!-- Assuming 'products' is a list of products for the selected category -->
            <label for="productSelect">Select Product:</label>
            <select id="productSelect" name="product">
                <option value = "" disabled selected>Select Product</option>
            </select>
            <input type="number" name="quantity" placeholder="Quantity" />
            <button type="submit" formaction="/order/clear"> Clear </button>
            <button type="submit">Add to Cart</button>
        </form>
    </div>
    <!-- Display added products -->
    <div class="sectionTwo">

        <ul>
            <li>Customer Name: ${Reciept.getCname()}</li>
            <li>Phone Number: ${(Reciept.getPhone_no()!= 0)?Reciept.getPhone_no():""}</li>
            <li>Products Added:
                <table>
                    <thead>
                    <tr>
                        <th>Product Name</th>
                        <th>Quantity</th>
                        <th>Price</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${Reciept.getCart()}" var="product">
                        <tr>
                            <td>${product.proname}</td>
                            <td>${product.quantity}</td>
                            <td>${product.price}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </li>
            <!-- Calculate total price -->
            <li>Total Price: ${Reciept.getTotalAmount()}</li>
        </ul>
        <p style="color: red; font-weight: bold; font-style: italic" >${Reciept.getMessage()}</p>
        <button type="submit" formaction="/order/confirm">Place Order</button>
    </div>
</div>
<!-- AJAX script to fetch and display products -->
<script>
    $(document).ready(function() {
        $('#categorySelect').change(function() {
            var selectedCategory = $(this).val();
            $.ajax({
                url: '/order/productByCategory',
                type: 'GET',
                data: { category: selectedCategory },
                dataType: 'json',
                success: function(products) {
                    $('#productSelect').empty();
                    $.each(products, function(index, product) {
                        $('#productSelect').append($('<option>', {
                            value: product.proId,
                            text: product.proName + ' ->  Price: Rs.' + product.price
                        }));
                    });
                },
                error: function(jqXHR, textStatus, errorThrown) {
                    console.error('Error fetching products: ' + textStatus, errorThrown);
                }
            });
        });
    });

    function limitInputLength(input) {
        // Check if the input value is negative or longer than 10 digits
        if (input.value < 0 || input.value.length > 10) {
            input.value = input.value.slice(0, 10); // Trim the value to 10 digits
        }
    }

    // Add event listener to handle input
    document.getElementById('phoneNumber').addEventListener('input', function(e) {
        // Remove any non-numeric characters entered
        this.value = this.value.replace(/[^0-9]/g, '');
        // Limit the length of the input to 10 digits
        if (this.value.length > 10) {
            this.value = this.value.slice(0, 10);
        }
    });

    // Prevent negative numbers by handling the 'keydown' event
    document.getElementById('phoneNumber').addEventListener('keydown', function(e) {
        if (e.key === '-' || e.key === '+') {
            e.preventDefault();
        }
    });
</script>
</body>
</html>
