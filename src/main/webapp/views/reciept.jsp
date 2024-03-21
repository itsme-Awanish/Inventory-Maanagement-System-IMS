<%@page language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="<c:url value="../reciept.css"/>">
    <title>Invoice Bill</title>
</head>
<body>
<div class="Invoice-wrapper" id="print-area">
    <div class="invoice">
        <div class="invoice-container">
            <div class="invoice-head">
                <div class="invoice-head-top">
                    <div class="invoice-head-top-left">
                        <h1>IMS</h1>
                    </div>
                    <div class="invoice-head-top-right">
                        <h3> Tax <br>
                            Invoice</h3>
                    </div>
                </div>
                <div class="hr"></div>
                <div class="invoice-head-middle">
                    <div class="invoice-head-middle-left">
                        <p><span class="text-bold">Name :</span> ${Reciept.getCname()}</p>
                        <p><span class="text-bold">Contact Number :</span> ${(Reciept.getPhone_no() != 0) ? Reciept.getPhone_no() : ""} </p>

                    </div>
                    <div class="invoice-head-middle-right">
                        <p><span class="text-bold">Order Id:</span>${Reciept.getOid()}</p>
                        <p><span class="text-bold">Date</span>:${Reciept.getDop()}</p>
                    </div>
                </div>

                <div class="overflow-view">
                    <div class="invoice-body">
                        <table>
                            <thead>
                            <tr>
                                <td class="text-bold">Product Name:</td>
                                <td class="text-bold">QTY:</td>
                                <td class="text-bold">Amount:</td>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${Reciept.getCart()}" var="product">
                                <tr>
                                    <td>${product.proname}</td>
                                    <td>${product.quantity}</td>
                                    <td class="text-end">${product.price}</td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>

                        <div class="invoice-body-buttom">
                            <div class="invoice-body-info-item border-bottom">
                                <div class="info-item-td text-end text-bold">
                                    Grand Total:
                                </div>
                                <div class="info-item-td text-end">${Reciept.getTotalAmount()}</div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="invoice-foot text-center">
                    <p><span class="text-bold text-center">
                            NOTE:
                        </span>This is computer generated recipt
                        and does not require physical signatuer.</p>

                    <div class="invoice-btns">
                        <form method="post">
                        <button type="submit" formaction="/order/clear" class="invoice-btns">
                            <span>Back</span>
                        </button>
                        <button type="button" class="invoice-btns" onclick="printInvoice()">
                            <span>Print</span>
                        </button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!----------------javascript---------->

<script>
    function printInvoice(){
        window.print();
    }
</script>

</body>
</html>