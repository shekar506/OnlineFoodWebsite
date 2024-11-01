<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.OnlineFood.model.OrderHistory, java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Order History</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background: linear-gradient(120deg, #f5f7fa, #c3cfe2);
            margin: 0;
            padding: 0;
        }
        .navbar {
            background-color: #343a40;
            padding: 15px 10px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
        }
        .navbar-brand {
            font-size: 28px;
            font-weight: bold;
            color: orange;
            text-transform: uppercase;
            letter-spacing: 1px;
        }
        .navbar-nav .nav-link {
            color: #f8d210 !important;
            padding: 10px 15px;
            border-radius: 5px;
            transition: background-color 0.3s ease;
        }
        .navbar-nav .nav-link:hover {
            background-color: orange;
            color: black !important;
        }
        .container {
            max-width: 800px;
            margin: 20px auto;
            padding: 30px;
            background-color: rgba(255, 255, 255, 0.9);
            border-radius: 12px;
            box-shadow: 0 6px 12px rgba(0, 0, 0, 0.3);
        }
        h2 {
            color: #333;
            margin-bottom: 20px;
        }
        .order-card {
            background-color: white;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            margin: 15px 0;
            padding: 20px;
            display: flex;
            align-items: center;
            justify-content: space-between;
            transition: transform 0.2s, box-shadow 0.2s;
        }
        .order-card:hover {
            transform: translateY(-3px);
            box-shadow: 0 6px 12px rgba(0, 0, 0, 0.15);
        }
        .order-details {
            flex: 1;
            margin-left: 20px;
        }
        .order-id {
            font-size: 1.2em;
            font-weight: bold;
            color: #333;
        }
        .order-info {
            color: #555;
            margin-bottom: 10px;
        }
        .order-actions {
            text-align: right;
        }
        .order-actions button {
            background-color: #f57c00;
            color: white;
            border: none;
            padding: 10px 15px;
            margin-left: 10px;
            border-radius: 5px;
            cursor: pointer;
            transition: background-color 0.3s;
        }
        .order-actions button:hover {
            background-color: #e66a00;
        }
        .help-button {
            background-color: #6c757d;
        }
        .help-button:hover {
            background-color: #5a6268;
        }
        .no-orders {
            text-align: center;
            font-size: 1.2em;
            color: #555;
            margin-top: 20px;
        }
    </style>
</head>
<body>

<!-- Navigation Bar -->
<nav class="navbar navbar-expand-lg">
    <a class="navbar-brand" href="#">OnlineFood</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav ms-auto">
            <li class="nav-item"><a class="nav-link" href="Home.jsp">Home</a></li>
            <li class="nav-item"><a class="nav-link" href="OrderHistory.jsp">Order History</a></li>
            <li class="nav-item"><a class="nav-link" href="Logout.jsp">Logout</a></li>
        </ul>
    </div>
</nav>

<div class="container">
    <h2>Past Orders</h2>
<%
    List<OrderHistory> orderHistoryList = (List<OrderHistory>) session.getAttribute("OrderHistory");
    if (orderHistoryList != null && !orderHistoryList.isEmpty()) {
        for (OrderHistory order : orderHistoryList) {
%>
    <div class="order-card">
        <div class="order-details">
            <div class="order-id"><%= order.getStatus() %></div>
            <div class="order-info">
                Order #<%= order.getOrder_id() %> | Delivered on <%= order.getOrderhistory_id() %>
            </div>
            <div class="order-info">Total Paid: ₹<%= order.getTotal() %></div>
        </div>
        <div class="order-actions">
            <form action="OrderDetails" method="post">
                <input type="hidden" name="order_id" value="<%= order.getOrder_id() %>">
                <button type="submit">View Details</button>
            </form>
            <button class="help-button">Help</button>
        </div>
    </div>
<%
        }
    } else {
%>
    <div class="no-orders">No past orders found.</div>
<%
    }
%>
</div>

</body>
</html>
