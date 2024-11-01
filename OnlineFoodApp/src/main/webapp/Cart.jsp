<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.OnlineFood.model.CartItem" %>
<%@ page import="com.OnlineFood.daoimpl.Cart" %>
<%@ page import="java.util.Map" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Your Cart</title>
    <style>
    /* Basic Reset */
    * {
        margin: 0;
        padding: 0;
        box-sizing: border-box;
    }

    body {
        background: linear-gradient(120deg, #f5f7fa, #c3cfe2);
        font-family: 'Arial', sans-serif;
    }

    /* Navbar Styling */
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

    .welcome-text {
        color: #f8d210;
        font-weight: bold;
        font-size: 18px;
        margin-left: 15px;
    }

    .navbar-toggler,
    .navbar-nav .nav-link {
        color: #f8d210 !important;
        background-color: #565e64;
        border-radius: 5px;
        padding: 10px 15px;
        transition: background-color 0.3s ease;
    }

    .navbar-nav .nav-link:hover {
        background-color: orange;
        color: black !important;
    }

    .cart-container {
        background-color: rgba(255, 255, 255, 0.9);
        border-radius: 12px;
        box-shadow: 0 6px 12px rgba(0, 0, 0, 0.3);
        padding: 30px;
        max-width: 800px;
        margin: 20px auto;
        color: #333;
    }

    h1 {
        color: #333;
        font-size: 2.5em;
        margin-bottom: 20px;
        text-align: center;
        font-weight: bold;
    }

    .cart-item {
        display: flex;
        gap: 20px;
        background-color: #f8f8f8;
        border-radius: 10px;
        padding: 20px;
        margin: 20px 0;
        box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        align-items: center;
        transition: box-shadow 0.3s;
    }

    .cart-item:hover {
        box-shadow: 0 6px 14px rgba(0, 0, 0, 0.2);
    }

    .cart-item img {
        width: 150px;
        height: auto;
        border-radius: 8px;
        box-shadow: 0 2px 4px rgba(0, 0, 0, 0.2);
    }

    .cart-details {
        flex-grow: 1;
    }

    .item-name {
        font-weight: bold;
        font-size: 1.4em;
        color: #333;
        margin: 5px 0;
    }

    .description, .price {
        color: #666;
        margin: 5px 0;
    }

    .quantity-update {
        display: flex;
        align-items: center;
        gap: 10px;
    }

    .form-container {
        display: flex;
        align-items: center;
        gap: 10px;
        margin-top: 10px;
    }

    .quantity-input {
        width: 50px;
        padding: 5px;
        font-size: 1em;
        text-align: center;
        border-radius: 5px;
        border: 1px solid #ccc;
        background-color: #fff;
    }

    .update-btn,
    .delete-btn {
        background-color: #ff6f00;
        color: white;
        border: none;
        padding: 10px 15px;
        border-radius: 5px;
        cursor: pointer;
        transition: background-color 0.3s;
    }

    .update-btn:hover {
        background-color: #ff6f00;
    }

    .delete-btn {
        background-color: rgb(255, 0, 0);
    }

    .delete-btn:hover {
        background-color: #c9302c;
    }

    .total {
        font-size: 1.5em;
        font-weight: bold;
        text-align: center;
        margin-top: 20px;
    }

    .btn {
        display: inline-block;
        padding: 10px 20px;
        background-color: #28a745;
        color: white;
        border: none;
        border-radius: 5px;
        text-decoration: none;
        font-size: 1.2em;
        margin: 20px auto;
        text-align: center;
        transition: background-color 0.3s;
    }

    .btn:hover {
        background-color: #218838;
    }
    </style>

    <script>
        // Apply bounce animation on button click
        function applyBounceEffect(element) {
            element.classList.add('animate-bounce');
            setTimeout(() => element.classList.remove('animate-bounce'), 500);
        }
    </script>
</head>
<body>
    <div class="cart-container">
        <h1>Your Cart</h1>
        <%
            Cart cart = (Cart) session.getAttribute("cart");
            if (cart != null && !cart.getItems().isEmpty()) {
                Map<Integer, CartItem> items = cart.getItems();
                double totalPrice = 0.0;
                for (CartItem item : items.values()) {
                    totalPrice += item.getPrice() * item.getQuantity();
        %>
            <div class="cart-item">
                <img src="<%= item.getImgpath()%>" alt="<%= item.getName() %>" class="item-image">
                <div class="cart-details">
                    <p class="item-name"><%= item.getName() %></p>
                    <p class="price">Price: ₹<%= item.getPrice() %></p>
                </div>
                <div class="quantity-update">
                    <form action="cart?itemId=<%=item.getItemId()%>" method="post" onsubmit="applyBounceEffect(this.querySelector('.update-btn'))">
                        <input type="number" name="quantity" value="<%=item.getQuantity() %>" class="quantity-input" min="1">
                        <input type="submit" value="Update" class="update-btn">
                        <input type="hidden" name="action" value="update">
                    </form>
                    <form action="cart?itemId=<%=item.getItemId()%>" method="post" onsubmit="applyBounceEffect(this.querySelector('.delete-btn'))">
                        <input type="submit" value="Delete" class="delete-btn">
                        <input type="hidden" name="action" value="delete">
                    </form>
                </div>
            </div>
        <%
                }
        %>
            <p class="total">Total Price: ₹<%= totalPrice %></p>
            <center><a href="CheckOut.jsp" class="btn">Proceed to Checkout</a></center>
        <%
            } else {
        %>
            <p>Your cart is empty.</p>
        <%
            }
        %>
    </div>
</body>
</html>
