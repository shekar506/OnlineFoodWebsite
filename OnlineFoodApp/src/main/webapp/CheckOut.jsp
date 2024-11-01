<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.OnlineFood.model.CartItem" %>
<%@ page import="com.OnlineFood.daoimpl.Cart" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Checkout</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-image: linear-gradient(120deg, #f5f7fa, #c3cfe2); /* Softer background gradient */
            background-size: cover;
            background-position: center;
            color: #333;
            display: flex;
            justify-content: center;
            padding: 30px;
        }
        .container {
            max-width: 600px; /* Reduced container width */
            width: 100%;
            padding: 20px;
            background-color: rgba(255, 255, 255, 0.9); /* Light background for contrast */
            border-radius: 8px; /* Slightly reduced border radius */
            box-shadow: 0 6px 20px rgba(0, 0, 0, 0.1);
            font-size: 14px; /* Smaller font size */
        }
        h1 {
            text-align: center;
            color: #ff7043; /* Updated color for headings */
            margin-bottom: 20px; /* Added margin for spacing */
        }
        .order-item, .promo-code-section, .address-section, .payment-options {
            margin-bottom: 15px; /* Reduced bottom margin */
            border-bottom: 1px solid #ddd; /* Added a bottom border for separation */
            padding-bottom: 10px; /* Added padding for better spacing */
        }
        .order-item {
            display: flex;
            justify-content: space-between;
            font-size: 16px; /* Adjusted font size for order items */
        }
        .total-amount {
            font-size: 18px; /* Increased size for better visibility */
            font-weight: bold;
            text-align: right;
            color: #ff7043; /* Keep the color consistent */
        }
        .promo-code-section input {
            width: 180px; /* Slightly smaller input field */
            padding: 5px;
            font-size: 14px;
            margin-right: 5px; /* Reduced margin */
        }
        .apply-btn, .confirm-btn {
            padding: 8px 12px; /* Slightly reduced button size */
            background-color: #ff7043; /* Button color */
            color: white;
            border: none;
            border-radius: 5px;
            font-size: 14px; /* Smaller font size */
            cursor: pointer;
            transition: background-color 0.3s ease;
        }
        .apply-btn:hover, .confirm-btn:hover {
            background-color: #e64a19; /* Darker hover effect */
        }
        .address-section input[type="radio"] {
            margin-right: 5px;
        }
        .new-address {
            margin-top: 10px;
        }
        input[name="newAddress"] {
            width: calc(100% - 20px); /* Responsive width */
            padding: 8px;
            border: 1px solid #ddd; /* Border for the new address input */
            border-radius: 4px; /* Rounded corners */
            margin-top: 5px; /* Space above input */
        }
    </style>
    <script>
        function applyPromoCode() {
            // Implement your promo code application logic here
            alert("Promo code applied!");
        }
    </script>
</head>
<body>
    <div class="container">
        <h1>Checkout</h1>

        <%
            Cart cart = (Cart) session.getAttribute("cart");
            if (cart != null && !cart.getItems().isEmpty()) {
                double totalPrice = 0.0;
                for (CartItem item : cart.getItems().values()) {
                    totalPrice += item.getPrice() * item.getQuantity();
        %>
            <div class="order-item">
                <span><strong><%= item.getName() %></strong> (x<%= item.getQuantity() %>)</span>
                <span>₹<%= item.getPrice() * item.getQuantity() %></span>
            </div>
        <%
                }
        %>
            <!-- Promo Code Section -->
            <div class="promo-code-section">
                <label for="promoCode">Promo Code:</label>
                <input type="text" id="promoCode" name="promoCode" placeholder="Enter promo code">
                <button type="button" class="apply-btn" onclick="applyPromoCode()">Apply</button>
            </div>

            <!-- Address Selection Section -->
            <div class="address-section">
                <h3>Select Delivery Address:</h3>
                <label><input type="radio" name="address" value="home" required> Home Address</label><br>
                <label><input type="radio" name="address" value="work"> Work Address</label><br>
                <label><input type="radio" name="address" value="new"> Enter New Address</label><br>
                <div class="new-address">
                    <input type="text" name="newAddress" placeholder="Type new address" disabled>
                </div>
            </div>

            <script>
                // Enable new address input only if the "Enter New Address" option is selected
                document.querySelectorAll('input[name="address"]').forEach(radio => {
                    radio.addEventListener('change', function() {
                        document.querySelector('input[name="newAddress"]').disabled = this.value !== 'new';
                    });
                });
            </script>

            <p class="total-amount">Total Amount: ₹<%= totalPrice %></p>

            <!-- Confirm Order Form -->
            <form action="CheckOut" method="post">
                <input type="hidden" name="totalAmount" value="<%= totalPrice %>">
                <input type="hidden" name="promoCode" id="hiddenPromoCode">

                <!-- Payment Options -->
                <div class="payment-options">
                    <h3>Select Payment Option:</h3>
                    <label><input type="radio" name="paymentMethod" value="credit" required> Credit Card</label><br>
                    <label><input type="radio" name="paymentMethod" value="debit"> Debit Card</label><br>
                    <label><input type="radio" name="paymentMethod" value="paypal"> PayPal</label><br>
                    <label><input type="radio" name="paymentMethod" value="cod"> Cash on Delivery</label>
                </div>

                <button type="submit" class="confirm-btn">Confirm Order</button>
            </form>
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
