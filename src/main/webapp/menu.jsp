<%@page import="model.Menu"%>
<%@page import="model.Restaurant"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Menu Details</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f9f9f9;
        }

        header {
            display: flex;
            justify-content: space-between; /* Distribute space between elements */
            align-items: center;
            background: linear-gradient(to right, #fc8019, #f56c3a);
            padding: 20px;
            color: white;
        }

        header h1 {
            text-align: center;
            flex: 1;
        }

        .user-buttons {
            display: flex;
            justify-content: flex-end; /* Align items to the right */
        }

        .dropdown {
            display: inline-block;
            position: relative;
        }

        .dropbtn {
            background-color: #ff5a60;
            color: white;
            padding: 10px 20px;
            font-size: 14px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            transition: 0.3s;
            margin-left: 10px;
        }

        .dropbtn:hover {
            background-color: #e04a52;
        }

        /* Dropdown Content (hidden by default) */
        .dropdown-content {
            display: none;
            position: absolute;
            background-color: #f9f9f9;
            min-width: 160px;
            box-shadow: 0px 8px 16px rgba(0, 0, 0, 0.2);
            z-index: 1;
            border-radius: 5px;
            padding: 10px 0;
        }

        /* Dropdown links */
        .dropdown-content a {
            color: black;
            padding: 12px 16px;
            text-decoration: none;
            display: block;
        }

        /* Change color when hovering over dropdown links */
        .dropdown-content a:hover {
            background-color: #ddd;
        }

        /* Show the dropdown content when the button is hovered */
        .dropdown:hover .dropdown-content {
            display: block;
        }

        .container {
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(250px, 1fr));
            gap: 20px;
            margin: 20px;
            padding: 0 20px;
        }

        .menu-item {
            background-color: white;
            border: 1px solid #e0e0e0;
            border-radius: 10px;
            padding: 15px;
            text-align: center;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            display: flex;
            flex-direction: column;
            justify-content: space-between;
        }

        .menu-item img {
            width: 100%;
            height: 200px;
            object-fit: cover;
            border-radius: 10px;
            margin-bottom: 10px;
        }

        .menu-item h2 {
            font-size: 1.5em;
            margin: 10px 0;
            color: #333;
        }

        .menu-item p {
            font-size: 14px;
            color: #555;
            margin: 5px 0;
        }

        .menu-item .price, .menu-item .rating {
            font-size: 16px;
            font-weight: bold;
            margin: 10px 0;
        }

        .menu-item form {
            margin-top: 10px;
        }

        .menu-item form p {
            display: flex;
            align-items: center;
            justify-content: center;
            gap: 10px;
        }

        .menu-item form input[type="number"] {
            width: 50px;
            padding: 5px;
            font-size: 14px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }

        .order-button {
            padding: 10px 20px;
            font-size: 14px;
            border: none;
            border-radius: 5px;
            background-color: #ff5a60;
            color: white;
            cursor: pointer;
            transition: 0.3s;
        }

        .order-button:hover {
            background-color: #e04a52;
        }

        .go-top {
            text-align: center;
            margin: 20px;
        }

        .go-top button {
            padding: 10px 20px;
            font-size: 14px;
            border: none;
            border-radius: 5px;
             background: linear-gradient(to right, #fc8019, #f56c3a);
            color: white;
            cursor: pointer;
            margin: 5px;
        }

        .go-top button:hover {
             background: linear-gradient(to right, #fc8019, #f56c3a);
        }

        footer {
            text-align: center;
            padding: 20px;
             background: linear-gradient(to right, #fc8019, #f56c3a);
            color: white;
        }
    </style>
</head>
<body>
    <header>
        <h1>Menu Details</h1>
        <!-- Dropdown for View More Button -->
        <div class="user-buttons">
            <div class="dropdown">
                <button class="dropbtn">View More</button>
                <div class="dropdown-content">
                    <a href="home.jsp">Home</a>
                    <a href="cart.jsp">Cart</a>
                   
                </div>
            </div>
        </div>
    </header>

    <div class="container">
        <% 
        Restaurant restaurant = (Restaurant) session.getAttribute("restaurantID");
        List<Menu> menu = (List<Menu>) session.getAttribute("MenuFetchOne");

        for(Menu menuList : menu) {
            if (menuList != null) { 
        %>
            <div class="menu-item">
                <img src="<%= menuList.getImage() %>" alt="<%= menuList.getName() %>">
                <h2><%= menuList.getName() %></h2>
                <p><strong>Description:</strong> <%= menuList.getDescription() %></p>
                <p><strong>Restaurant ID:</strong> <%= menuList.getRestaurantId() %></p>
                <p><strong>Restaurant Name:</strong> <%= restaurant.getName() %></p>
                <p class="price">Price: ₹<%= menuList.getPrice() %></p>
                <p class="rating">Rating: ⭐ <%= menuList.getRating() %></p>
                <p class="availability"><strong>Availability:</strong> <%= menuList.getIsAvailable() %></p>

                <form action="CartServlet?MenuId=<%=menuList.getMenuId()%>" method="post">
                    <p>
                        <span>Quantity:</span> 
                        <input type="number" name="quantity" value="1" min="1" max="10" />
                    </p>
                    <input type="submit" value="Add to Cart" class="order-button">
                    <input type="hidden" name="act" value="add">
                </form>
            </div>
        <% } else { %>
            <p>No menu data available!</p>
        <% } } %>
    </div>

    <div class="go-top">
        <a href="#top"><button>Go to Top</button></a>
    </div>

    <footer>
        <p>&copy; 2024 Restaurant Menu Inc. All rights reserved.</p>
    </footer>
</body>
</html>
