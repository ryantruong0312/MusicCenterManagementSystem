<%-- 
    Document   : complete
    Created on : Aug 19, 2022, 12:39:05 PM
    Author     : tlminh
--%>

<%@page import="java.util.List"%>
<%@page import="minhtl.orders.OrderDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Order Tracking Page</title>
        <link rel="stylesheet" href="css/header.css" type="text/css">
        <link rel="stylesheet" href="css/orderTracking.css" type="text/css">
    </head>
    <body>
        <%@include file="header.jsp"%>
        <main>
            <h1>ORDER TRACKING</h1>
            <%                if (loginUser != null) {
            %>
            <div class="trackbox">
                <form action="MainController">
                    Order ID <input type="text" name="orderId" value="${param.orderId}">
                    <input type="submit" name="action" value="Track">
                </form>
            </div>
            <%
            } else {
            %>
            <h3>You have to log in to track orders</h3>
            <%
                }
                OrderDTO order = (OrderDTO) request.getAttribute("ORDER");
                List<String> orderList = (List<String>) request.getAttribute("ORDER_LIST");
                if (order != null) {
            %>
            <br>
            <span>Order ID:</span> <%=order.getOrderId()%> <br><br>
            <span>Customer:</span> <%=order.getCusName()%> <br><br>
            <span>Contact Number:</span> <%=order.getCusPhone()%> <br><br>
            <span>Email:</span> <%=order.getCusEmail()%> <br><br>
            <span>Courses:</span><br>
            <%
                int count = 1;
                for (String course : orderList) {
            %>
            <h4><%=count%>-<%=course%></h4>
            <%
                    count++;
                }
            %>
            <span>Total amount:</span> $<%=order.getOrderTotal()%> <br><br>
            <span>Booking date:</span> <%=order.getOrderDate()%> <br><br>
            <span>Payment method:</span> <%=order.getPaymentType()%> <br><br>
            <span>Payment Status:</span> <%=order.getPaymentStatus()%> <br><br>
            <%
                }
            %>
        </main>
    </body>
</html>
