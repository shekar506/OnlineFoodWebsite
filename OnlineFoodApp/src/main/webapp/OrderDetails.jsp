<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.OnlineFood.model.Orders" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Order Details</title>
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background-image: linear-gradient(120deg, #f5f7fa, #c3cfe2);
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            animation: fadeIn 1s ease; /* Fade-in animation */
        }

        @keyframes fadeIn {
            from { opacity: 0; }
            to { opacity: 1; }
        }

        .container {
            background-color: #fff;
            border-radius: 10px;
            padding: 30px;
            box-shadow: 0 6px 20px rgba(0, 0, 0, 0.15); /* Deeper shadow for depth */
            width: 400px;
            text-align: center;
            transition: transform 0.3s ease; /* Scale effect on hover */
        }

        .container:hover {
            transform: scale(1.02); /* Slightly enlarge on hover */
        }

        .container h2 {
            font-size: 1.8em;
            color: #333;
            margin-bottom: 20px;
        }

        .order-info {
            margin-bottom: 20px;
            text-align: left;
        }

        .order-info div {
            font-size: 1em;
            margin-bottom: 10px;
        }

        .order-info div span {
            font-weight: bold;
            color: #f57c00;
        }

        .image {
            margin: 20px 0;
        }

        .image img {
            max-width: 150px;
            border-radius: 10px;
            transition: transform 0.2s ease;
        }

        .image img:hover {
            transform: scale(1.1); /* Zoom effect on hover */
        }

        .total-amount {
            font-size: 1.6em;
            color: #f57c00;
            margin: 10px 0;
            font-weight: bold; /* Emphasize total amount */
        }

        .status {
            font-size: 1.4em;
            color: #28a745;
            margin: 10px 0;
        }

        .action-buttons {
            display: flex;
            justify-content: center;
            margin-top: 20px; /* Spacing above buttons */
        }

        .btn {
            background-color: #f57c00;
            color: white;
            border: none;
            padding: 10px 20px;
            border-radius: 5px;
            cursor: pointer;
            font-size: 1em;
            transition: background-color 0.3s ease, transform 0.2s ease;
        }

        .btn:hover {
            background-color: #e66a00;
            transform: translateY(-2px); /* Lift effect on hover */
        }

        .btn-secondary {
            background-color: #6c757d;
            margin-left: 10px;
        }

        .btn-secondary:hover {
            background-color: #5a6268;
            transform: translateY(-2px); /* Lift effect on hover */
        }

        /* Responsive Styles */
        @media (max-width: 600px) {
            .container {
                width: 90%; /* Responsive width */
                padding: 20px; /* Adjusted padding */
            }

            .container h2 {
                font-size: 1.6em; /* Smaller header size */
            }

            .total-amount,
            .status {
                font-size: 1.4em; /* Adjust font size for mobile */
            }

            .btn {
                padding: 8px 16px; /* Smaller button padding */
                font-size: 0.9em; /* Smaller button text */
            }
        }
    </style>
</head>
<body>

<% Orders order = (Orders) session.getAttribute("OrderDetails"); %>

<div class="container">
    <h2>Order Details</h2>
    
    <% if (order != null) { %>
        <div class="total-amount">
            Total: ₹<%= order.getTotalAmount() %>
        </div>

        <div class="status">
            Status: <%= order.getStatus() %>
        </div>
        
        <div class="image">
            <img src="<%= session.getAttribute("ItemImage") %>" alt="Item Image" />
        </div>
        
        <div class="itemName">
            Item Name: <span><%= session.getAttribute("Item") %></span>
        </div>
    <% } else { %>
        <div class="error-message" style="color: red;">
            Error: Order details not found.
        </div>
    <% } %>

    <div class="action-buttons">
        <form action="OrderHistory.jsp" method="get" style="display:inline;">
            <button type="submit" class="btn btn-secondary">Back</button>
        </form>
    </div>

</div>

</body>
</html>
