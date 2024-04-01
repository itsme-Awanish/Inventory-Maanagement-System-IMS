<%@page language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script src="https://d3js.org/d3.v5.min.js"></script>
    <link rel="stylesheet" href="<c:url value='../dashboard.css'/>">
    <title>Dashboard</title>
</head>

<body>

<div class="navbar">
    <div>
        <a href="${pageContext.request.contextPath}/dashboard" class="nav-link active"><i class="fa-solid fa-house"></i>Home</a>
        <a href="${pageContext.request.contextPath}/category" class="nav-link"><i class="fa-solid fa-list"></i>Category</a>
        <a href="${pageContext.request.contextPath}/product" class="nav-link"><i class="fa-solid fa-bag-shopping"></i>Product</a>
        <a href="${pageContext.request.contextPath}/order/" class="nav-link"><i class="fa-solid fa-cart-shopping"></i>Place Orders</a>
    </div>
    <div id="username"><i class="fa-solid fa-user"></i><span>Username</span>
        <div id="logout">
            <a href="#">Logout</a>
        </div>
    </div>
</div>

<div class="main">
    <div id="charts">
        <div class="chart"><svg width="600" height="300" id="pie"></svg>
            <div id="legend"></div>
            <h1>Stock Report</h1>
        </div>
        <div class="chart">
            <div id="line-chart"></div>
            <h1>Sales Report</h1>
        </div>
    </div>

    <div class="inventory-details">
        <h2>Inventory Details</h2>
        <ul style="font-size: 20px;">
            <li>Inventory Size: <span style="color: maroon; font-weight: bolder">${inventorySize}</span></li>
            <li>Last Month Turnover: <span style="color: maroon; font-weight: bolder">&#8377;${(lastMonthSales != null)?lastMonthSales:0}</span></li>
            <li>This month Turnover: <span style="color: maroon; font-weight: bolder">&#8377;${thisMonthSales}</span></li>
        </ul>
        <!-- Inventory details go here -->
    </div>

    <div class="order-history">
        <h2>Recent Order History</h2>
        <table class="order-table">
            <thead>
            <tr>
                <th>Customer Name</th>
                <th>Order ID</th>
                <th>Products</th>
                <th>Date of Purchase</th>
                <th>Total Amount</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var = "roh" items="${recentOrderHistory}">
                <tr>
                    <td>${roh.custName}</td>
                    <td>${roh.order}</td>
                    <td>
                        <ul>
                            <c:forEach var = "product" items="${roh.crt}">
                                <li>${product.proname} for QTY <span style="color: green;font-weight: bolder">${product.quantity}</span> : <span style="color: maroon;font-weight: bolder">&#8377;${product.price}</span></li>
                            </c:forEach>
                        </ul>
                    </td>
                    <td>${roh.dop}</td>
                    <td>&#8377;${roh.totalAmount}</td>
                </tr>
            </c:forEach>
            <!-- Add more rows here -->
            </tbody>
        </table>
        <!-- Order history goes here -->
    </div>
</div>

<script>
    var data = [
        <c:forEach var="item" items="${stockReport}" varStatus="loop">
        { key: "${item.categoryName}", value: ${item.quantity} }<c:if test="${!loop.last}">,</c:if>
        </c:forEach>
    ]; // your array list
    console.log(data)
    var width = 240, height = 300;
    var colors = data.map(function () {
        var color;
        do {
            color = '#' + (Math.random() * 0xFFFFFF << 0).toString(16);
        } while (color === '#ffffff'); // Avoid white colors
        return color;
    }); // Generate random colors
    var color = d3.scaleOrdinal().domain(data.map(function (d) { return d.key; })).range(colors);
    var pie = d3.pie().value(function (d) { return d.value; });
    var arc = d3.arc().innerRadius(0).outerRadius(Math.min(width, height) / 2);
    var svg = d3.select("#pie").append("g").attr("transform", "translate(" + width / 2 + "," + height / 2 + ")");
    var g = svg.selectAll(".arc").data(pie(data)).enter().append("g").attr("class", "arc");
    g.append("path").attr("d", arc).style("fill", function (d, i) { return color(i); });

    // Add a legend
    var legend = d3.select("#legend").selectAll(".legend").data(pie(data)).enter().append("div").attr("class", "legend");
    legend.append("span").style("display", "inline-block").style("width", "18px").style("height", "18px").style("margin-right", "4px").style("background-color", function (d, i) { return color(i); });
    legend.append("span").text(function (d) { return d.data.key + ": " + (d.data.value / d3.sum(data, function (d) { return d.value; }) * 100).toFixed(2) + "%"; });



    // line chart
    var lineData = [
        <c:forEach var="item" items="${salesReport}" varStatus="loop">
        { date: "${item.dop}", value: ${item.price} }<c:if test="${!loop.last}">,</c:if>
        </c:forEach>
    ];
    lineData.sort(function (a, b) {
        return a.date - b.date;
    });
    var margin = { top: 20, right: 50, bottom: 30, left: 50 };
    var width = lineData.length * 500; // Adjust as needed
    var height = 300 - margin.top - margin.bottom;
    var parseTime = d3.timeParse("%Y-%m-%d");
    var x = d3.scaleTime().range([0, width]);
    var y = d3.scaleLinear().range([height, 0]);
    var line = d3.line().x(function (d) { return x(d.date); }).y(function (d) { return y(d.value); });

    var svg = d3.select("#line-chart").style("overflow-x", "scroll").append("svg").attr("width", width + margin.left + margin.right).attr("height", height + margin.top + margin.bottom).append("g").attr("transform", "translate(" + margin.left + "," + margin.top + ")");
    lineData.forEach(function (d) {
        d.date = parseTime(d.date);
        d.value = +d.value;
    });
    x.domain([parseTime("2024-01-01"), d3.max(lineData, function (d) { return d.date; })]);
    y.domain([0, d3.max(lineData, function (d) { return d.value; })]);

    svg.append("path").data([lineData]).attr("class", "line").attr("d", line);
    svg.selectAll(".dot").data(lineData).enter().append("circle").attr("class", "dot").attr("cx", function (d) { return x(d.date); }).attr("cy", function (d) { return y(d.value); }).attr("r", 5);
    svg.selectAll(".text").data(lineData).enter().append("text").attr("x", function (d) { return x(d.date); }).attr("y", function (d) { return y(d.value); }).text(function (d) { return d3.timeFormat("%d-%b")(d.date) + ": " + d.value; }).style("font-size", "12px").attr("dx", "-30").attr("dy", "-10");    svg.append("g").attr("transform", "translate(0," + height + ")").call(d3.axisBottom(x));
    svg.append("g").call(d3.axisLeft(y));
</script>
</script>

</body>

</html>