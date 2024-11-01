<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Order Confirmation</title>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            background-image: linear-gradient(120deg, #f5f7fa, #c3cfe2);
            color: #333;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
            animation: fadeIn 1s ease; /* Added fade-in animation */
        }

        @keyframes fadeIn {
            from { opacity: 0; }
            to { opacity: 1; }
        }

        .confirmation-container {
            max-width: 600px;
            width: 100%;
            padding: 30px;
            background-color: #ffffff;
            border-radius: 15px;
            box-shadow: 0 8px 30px rgba(0, 0, 0, 0.2); /* Stronger shadow */
            text-align: center;
            color: #333;
            transition: transform 0.2s ease; /* Added hover effect */
        }

        .confirmation-container:hover {
            transform: scale(1.02); /* Slight scale on hover */
        }

        .confirmation-header {
            font-size: 28px;
            color: #4CAF50;
            margin-bottom: 15px; /* Added margin for spacing */
        }

        .confirmation-message {
            font-size: 16px;
            margin: 15px 0;
            color: #555;
            line-height: 1.5; /* Improved line height for readability */
        }

        .order-details {
            margin: 20px 0;
            padding: 15px;
            background-color: #f8f8f8;
            border-radius: 10px;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
        }

        .thank-you {
            font-size: 16px;
            color: #777;
            margin-top: 10px;
        }

        .home-button {
            display: inline-block;
            margin-top: 20px;
            padding: 12px 25px;
            background-color: #4CAF50;
            color: #fff;
            border-radius: 8px;
            text-decoration: none;
            font-size: 16px;
            transition: background-color 0.3s ease, transform 0.2s ease; /* Added transform effect */
        }

        .home-button:hover {
            background-color: #45a049;
            transform: translateY(-2px); /* Slight upward movement on hover */
        }

        /* Responsive Styles */
        @media (max-width: 600px) {
            .confirmation-container {
                padding: 20px; /* Reduced padding on smaller screens */
            }
            .confirmation-header {
                font-size: 24px; /* Smaller header size */
            }
            .confirmation-message,
            .thank-you {
                font-size: 14px; /* Smaller font for messages */
            }
            .home-button {
                padding: 10px 20px; /* Smaller button padding */
            }
        }
    </style>
</head>
<body>
    <div class="confirmation-container">
        <h1 class="confirmation-header">Order Placed Successfully!</h1>
        <p class="confirmation-message">Thank you for your order! Your delicious meal is being prepared and will be delivered soon.</p>
        
        <div class="order-details">
            <p><strong>Order ID:</strong> #<%= session.getAttribute("orderId") %></p>
            <p><strong>Total Amount:</strong> <%= session.getAttribute("totalPrice") %></p>
        </div>

        <p class="thank-you">We hope you enjoy your meal!</p>
        <a href="Home.jsp" class="home-button">Go to Home</a>
    </div>
</body>
</html>
