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
            <li>Inventory Size: </li>
            <li>Last Month Turnover: </li>
            <li>This month Turnover: </li>
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
            <tr>
                <td>John Doe</td>
                <td>12345</td>
                <td>
                    <ul>
                        <li>Product 1</li>
                        <li>Product 2</li>
                        <li>Product 3</li>
                    </ul>
                </td>
                <td>2024-01-01</td>
                <td>$100</td>
            </tr>
            <tr>
                <td>Jane Smith</td>
                <td>67890</td>
                <td>
                    <ul>
                        <li>Product 4</li>
                        <li>Product 5</li>
                        <li>Product 6</li>
                    </ul>
                </td>
                <td>2024-01-15</td>
                <td>$150</td>
            </tr>
            <!-- Add more rows here -->
            </tbody>
        </table>
        <!-- Order history goes here -->
    </div>
</div>

<script>
    var data = [{ key: "Label1", value: 10 }, { key: "Label2", value: 20 }, { key: "Label3", value: 30 }, { key: "Label4", value: 40 }]; // your array list
    var width = 300, height = 300;
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
    var lineData = [{ date: "2024-01-20", value: 10 }, { date: "2024-02-01", value: 20 }, { date: "2024-03-01", value: 30 }, { date: "2024-04-01", value: 40 }]; // your sales data
    var margin = { top: 20, right: 40, bottom: 30, left: 50 };
    var width = lineData.length * 200; // Adjust as needed
    var height = 300 - margin.top - margin.bottom;
    var parseTime = d3.timeParse("%Y-%m-%d");
    var x = d3.scaleTime().range([0, width]);
    var y = d3.scaleLinear().range([height, 0]);
    var line = d3.line().x(function (d) { return x(d.date); }).y(function (d) { return y(d.value); });

    var svg = d3.select("#line-chart").append("svg").attr("width", width + margin.left + margin.right).attr("height", height + margin.top + margin.bottom).append("g").attr("transform", "translate(" + margin.left + "," + margin.top + ")");
    lineData.forEach(function (d) {
        d.date = parseTime(d.date);
        d.value = +d.value;
    });
    x.domain([parseTime("2024-01-01"), d3.max(lineData, function (d) { return d.date; })]);
    y.domain([0, d3.max(lineData, function (d) { return d.value; })]);

    svg.append("path").data([lineData]).attr("class", "line").attr("d", line);
    svg.selectAll(".dot").data(lineData).enter().append("circle").attr("class", "dot").attr("cx", function (d) { return x(d.date); }).attr("cy", function (d) { return y(d.value); }).attr("r", 5);
    svg.selectAll(".text").data(lineData).enter().append("text").attr("x", function (d) { return x(d.date); }).attr("y", function (d) { return y(d.value); }).text(function (d) { return d3.timeFormat("%Y-%m-%d")(d.date); }).style("font-size", "12px").attr("dx", "-30").attr("dy", "-10");
    svg.append("g").attr("transform", "translate(0," + height + ")").call(d3.axisBottom(x));
    svg.append("g").call(d3.axisLeft(y));
</script>
</script>

</body>

</html>