<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.OnlineFood.model.menu, java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Menu Details</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
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

        .search-container {
            text-align: center;
            margin: 20px 0;
        }

        .search-input {
            width: 60%;
            padding: 10px;
            font-size: 1em;
            border: 1px solid #ccc;
            border-radius: 5px;
            color: #333;
        }

        .menu-container {
            background-color: rgba(255, 255, 255, 0.9);
            border-radius: 12px;
            box-shadow: 0 6px 12px rgba(0, 0, 0, 0.3);
            padding: 30px;
            max-width: 800px;
            margin: 20px auto;
            color: #333;
        }

        h2 {
            color: #333;
            font-size: 2em;
            margin-bottom: 20px;
            text-align: center;
            font-weight: bold;
        }

        .menu-item {
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

        .menu-item:hover {
            box-shadow: 0 6px 14px rgba(0, 0, 0, 0.2);
        }

        .menu-item img {
            width: 150px;
            height: auto;
            border-radius: 8px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.2);
        }

        .menu-details {
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

        .add-to-cart-button {
            background-color: #333;
            color: white;
            border: none;
            padding: 10px 15px;
            border-radius: 5px;
            cursor: pointer;
            transition: background-color 0.3s;
        }

        .add-to-cart-button:hover {
            background-color: #555;
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
            <li class="nav-item"><a class="nav-link" href="Cart.jsp">Cart</a></li>
            <li class="nav-item"><a class="nav-link" href="OrderHistory.jsp">Order History</a></li>
        </ul>
    </div>
</nav>

<!-- Search Bar -->
<div class="search-container">
    <input type="text" id="searchInput" class="search-input" placeholder="Search for dishes...">
</div>

<%
    List<menu> menuList = (List<menu>) session.getAttribute("menu");
    if (menuList != null && !menuList.isEmpty()) {
%>
    <div class="menu-container">
        <h2>Menu Details</h2>
        <% for (menu m : menuList) { %>
            <div class="menu-item">
                <img src="<%= m.getImgpath() %>" alt="<%= m.getItemName() %>">
                <div class="menu-details">
                    <div class="item-name"><%= m.getItemName() %></div>
                    <div class="description"><%= m.getDescription() %></div>
                    <div class="price">Price: ₹<%= m.getPrice() %></div>
                    <form action="cart?itemId=<%=m.getMenu_id()%>" method="post" class="form-container">
                        <label for="quantity-<%=m.getMenu_id()%>">Quantity:</label>
                        <input type="number" id="quantity-<%=m.getMenu_id()%>" name="quantity" value="1" min="1" class="quantity-input">
                        <input type="submit" value="Add to Cart" class="add-to-cart-button" aria-label="Add <%= m.getItemName() %> to cart">
                        <input type="hidden" name="action" value="add">
                    </form>
                </div>
            </div>
        <% } %>
    </div>
<%
    } else {
%>
    <div class="menu-container">
        <h2>No Menu Found</h2>
        <p>Please select a menu item.</p>
    </div>
<%
    }
%>

<script>
    const searchInput = document.getElementById('searchInput');
    searchInput.addEventListener('input', function() {
        const value = searchInput.value.toLowerCase();
        const menuItems = document.querySelectorAll('.menu-item');
        menuItems.forEach(item => {
            const itemName = item.querySelector('.item-name').textContent.toLowerCase();
            item.style.display = itemName.includes(value) ? 'flex' : 'none';
        });
    });
</script>
</body>
</html>
