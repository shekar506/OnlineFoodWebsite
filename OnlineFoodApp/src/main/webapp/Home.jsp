<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List,com.OnlineFood.model.Restaurant,com.OnlineFood.model.User" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>OnlineFood - Home</title>
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

        /* Search and Filter */
        .search-filter {
            display: flex;
            justify-content: center;
            align-items: center;
            margin: 30px 0;
        }

        .search-bar {
            width: 300px;
            border-radius: 8px;
            padding: 12px;
            border: 1px solid #ccc;
            transition: box-shadow 0.3s ease;
        }

        .search-bar:focus {
            box-shadow: 0 0 10px rgba(0, 123, 255, 0.3);
        }

        .btn-primary {
            background-color: orange;
            border: none;
            color: #fff;
            padding: 10px 15px;
            border-radius: 8px;
            transition: background-color 0.3s ease;
        }

        .btn-primary:hover {
            background-color: #f7a439;
        }

        /* Restaurant Card Styling */
        .restaurant-card {
            background: #fff;
            border-radius: 10px;
            padding: 15px;
            box-shadow: 0 8px 16px rgba(0, 0, 0, 0.15);
            transition: transform 0.3s ease, box-shadow 0.3s ease;
            width: 100%;
            max-width: 350px;
            margin: 20px auto;
            text-align: center;
            text-decoration: none;
            color: inherit;
        }

        .restaurant-card:hover {
            transform: scale(1.05);
            box-shadow: 0 12px 24px rgba(0, 0, 0, 0.3);
        }

        .restaurant-card img {
            width: 100%;
            height: 180px;
            object-fit: cover;
            border-radius: 8px;
            margin-bottom: 15px;
        }

        .rating {
            background-color: #28a745;
            color: white;
            padding: 5px 10px;
            border-radius: 5px;
            font-size: 0.9em;
            font-weight: bold;
            display: inline-block;
            margin-top: 8px;
        }

        .restaurant-info h3 {
            font-size: 1.4em;
            color: #333;
            font-weight: 600;
            margin-bottom: 10px;
        }

        .restaurant-info p {
            font-size: 0.95em;
            color: #666;
            margin-bottom: 5px;
        }

        /* Responsive Grid */
        .container {
            padding-top: 30px;
        }
    </style>
</head>
<body>
    <nav class="navbar navbar-expand-lg">
        <div class="container-fluid">
            <a class="navbar-brand" href="#">OnlineFood</a>
            <% User u = (User) session.getAttribute("user"); if (u != null) { %>
                <span class="welcome-text">Welcome, <%= u.getUsername() %>!</span>
            <% } %>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav ms-auto mb-2 mb-lg-0">
                    <% if (u == null) { %>
                    <li class="nav-item">
                        <a class="nav-link" href="Login.html">Sign In</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="Register.html">Sign Up</a>
                    </li>
                    <% } else { %>
                    <li class="nav-item">
                        <a class="nav-link" href="OrderHistory">Order History</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="LogoutServlet">Log Out</a>
                    </li>
                    <% } %>
                </ul>
            </div>
        </div>
    </nav>

    <!-- Search and Filter -->
    <div class="container">
        <div class="search-filter">
            <input type="text" class="form-control search-bar" placeholder="Search restaurants..." id="search-bar">
            <button class="btn btn-primary ms-3" id="search-button">Search</button>
        </div>
    </div>

    <div class="container">
        <div class="row justify-content-center">
            <% 
                List<Restaurant> list = (List<Restaurant>) session.getAttribute("restaurant");
                String searchTerm = request.getParameter("search");
                if (list == null || list.isEmpty()) { %>
                <p class="text-center">Please Login To Continue</p>
            <% } else {
                    boolean found = false;
                    for (Restaurant e : list) {
                        if (searchTerm == null || searchTerm.isEmpty() || e.getName().toLowerCase().contains(searchTerm.toLowerCase())) {
                            found = true;
            %>
            <div class="col-md-4 mb-4">
                <a href="ViewMenu?id=<%= e.getRestaurant_id() %>" class="restaurant-card">
                    <img src="<%= e.getImgpath() %>" alt="Image of <%= e.getName() %>">
                    <div class="restaurant-info">
                        <h3><%= e.getName() %> <span class="rating">⭐ <%= e.getRating() %></span></h3>
                        <p><strong>Cuisine:</strong> <%= e.getCuisinetype() %></p>
                        <p><strong>Delivery in:</strong> <%= e.getDeliveryttime() %> minutes</p>
                    </div>
                </a>
            </div>
            <% 
                        } 
                    } 
                    if (!found) { %>
                <p class="text-center">No matching restaurants found.</p>
            <% } 
            } %>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    <script>
        document.getElementById('search-button').addEventListener('click', function() {
            var searchTerm = document.getElementById('search-bar').value;
            window.location.href = '?search=' + encodeURIComponent(searchTerm);
        });
    </script>
</body>
</html>
