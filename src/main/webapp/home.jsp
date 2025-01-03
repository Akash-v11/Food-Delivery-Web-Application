
<%@page import="model.Restaurant"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home Page</title>

<style>
    /* Resetting some default styles */
    * {
        margin: 0;
        padding: 0;
        box-sizing: border-box;
    }

    body {
        font-family: 'Poppins', sans-serif;
        background-color: #f9fafb;
        color: #333;
        line-height: 1.6;
    }

    /* Header styling */
    header {
        background: linear-gradient(to right, #fc8019, #f56c3a); /* Vibrant gradient */
        color: white;
        padding: 30px 50px;
        text-align: center;
        font-size: 36px;
        font-weight: 600;
        border-bottom: 4px solid #f56c3a;
        box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
    }

    /* User button container */
    .user-buttons {
        position: absolute;
        top: 20px;
        right: 50px;
        display: flex;
        gap: 20px;
    }

    .user-buttons a {
        background-color: #ffffff;
        color: #fc8019;
        padding: 12px 20px;
        border-radius: 8px;
        text-decoration: none;
        font-size: 16px;
        font-weight: bold;
        box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
        transition: background-color 0.3s, transform 0.3s;
    }

    .user-buttons a:hover {
        background-color: #f56c3a;
        color: white;
        transform: translateY(-3px);
    }

    /* Main container for restaurants */
    .container {
        max-width: 1200px;
        margin: 40px auto;
        background-color: #fff;
        border-radius: 15px;
        padding: 40px;
        box-shadow: 0 8px 24px rgba(0, 0, 0, 0.1);
        overflow: hidden;
    }

    /* Restaurant Cards */
    .restaurant-cards {
        display: grid;
        grid-template-columns: repeat(auto-fill, minmax(280px, 1fr)); /* Flexible column size */
        gap: 30px;
        justify-items: center;
    }

    .restaurant-card {
        background: #fff;
        border-radius: 12px;
        box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
        padding: 20px;
        text-align: center;
        transition: transform 0.3s, box-shadow 0.3s, background-color 0.3s;
        cursor: pointer;
        overflow: hidden;
        position: relative;
    }

    .restaurant-card:hover {
        transform: translateY(-5px);
        box-shadow: 0 12px 30px rgba(0, 0, 0, 0.2);
        background-color: #f8f8f8;
    }

    .restaurant-card img {
        width: 100%;
        height: 200px;
        object-fit: cover;
        border-radius: 12px;
        transition: transform 0.4s ease-in-out;
    }

    .restaurant-card:hover img {
        transform: scale(1.1);
    }

    .restaurant-card h3 {
        font-size: 22px;
        font-weight: bold;
        color: #333;
        margin-top: 20px;
        transition: color 0.3s;
    }

    .restaurant-card:hover h3 {
        color: #fc8019;
    }

    .restaurant-card .info-item {
        margin: 10px 0;
        color: #555;
        font-size: 16px;
    }

    .restaurant-card .info-item strong {
        color: #fc8019;
    }

    .restaurant-card button {
        background-color: #fc8019;
        color: white;
        padding: 12px 20px;
        border: none;
        border-radius: 8px;
        cursor: pointer;
        font-size: 16px;
        font-weight: bold;
        transition: background-color 0.3s, transform 0.2s;
    }

    .restaurant-card button:hover {
        background-color: #f56c3a;
        transform: translateY(-2px);
    }

    /* Footer */
    footer {
        text-align: center;
        padding: 20px;
        background: linear-gradient(to right, #fc8019, #f56c3a); /* Vibrant gradient */
        color: white;
        font-size: 14px;
        margin-top: 40px;
        border-top: 4px solid #fc8019;
    }

    /* Responsive design adjustments */
    @media (max-width: 768px) {
        header {
            font-size: 28px;
        }

        .user-buttons {
            right: 20px;
        }

        .restaurant-cards {
            grid-template-columns: repeat(auto-fill, minmax(200px, 1fr)); /* More flexible on smaller screens */
        }

        .restaurant-card {
            padding: 15px;
        }

        .restaurant-card button {
            width: 100%;
            padding: 14px;
        }
    }
</style>

</head>
<body>

	<% 
    // Get the list of restaurants
    List<Restaurant> restaurantList = (List<Restaurant>) session.getAttribute("restaurantDisplay");
  	System.out.println("restaurantList in home.jsp:-  " + restaurantList);
    // Display restaurant cards
    %>
  
  
<header>
    Welcome to AK Foods
    <!-- User Buttons in the Top-Right Corner -->
    <div class="user-buttons">
 		<a href="logoutservlet">Logout</a>
        <a href="userdetails.jsp">Profile</a>
        <a href="cart.jsp">View Cart</a>       
    </div>
</header>

<div class="container">
    
    <div class="restaurant-cards">
        <% 
        for (Restaurant restaurant : restaurantList)
        { 
        %>
            <div class="restaurant-card">
                <h3><%= restaurant.getName() %></h3>
                <img src="<%= restaurant.getImage() %>" alt="Restaurant Image">
                <div class="info-item"><strong>Cuisine:</strong> <%= restaurant.getCuisinetype() %></div>
                <div class="info-item"><strong>Rating:</strong> <%= restaurant.getRating() %></div>
                <div class="info-item"><strong>Delivery Time:</strong> <%= restaurant.getDeliverytime() %></div>
                <div class="info-item"><strong>Status:</strong> <%= restaurant.getIsactive() %></div>
                <a href="ShowMenu?Restaurantid=<%= restaurant.getRestaurantid() %>"><button>View Menu</button></a>
            </div> 
        <% 
        }
        %>
    </div>
</div>

<footer>
    &copy; 2024 TAP Eats. All Rights Reserved.
</footer>
 
</body>
</html>
 