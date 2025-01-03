<%@page import="model.CartItem"%>
<%@page import="daoImplementation.CartDaoImplementation"%>
<%@page import="java.util.List"%>
<%@ page import="java.util.LinkedHashMap" %>
<%@ page import="java.util.Map" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Shopping Cart</title>
    <meta name="description" content="Your Shop - View and manage items in your shopping cart">
    <style>
        body {
            margin: 0;
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            display: flex;
            flex-direction: column;
            min-height: 100vh;
        }

        header {
            background-color: #FF6347;
            color: white;
            padding: 10px 20px;
            display: flex;
            justify-content: space-between;
            align-items: center;
        }

        header .logo {
            font-size: 24px;
            font-weight: bold;
        }

        header nav a {
            color: white;
            text-decoration: none;
            margin-left: 20px;
            font-size: 16px;
        }

        .cart-container {
            max-width: 1200px;
            margin: 20px auto;
            padding: 20px;
            background: white;
            border-radius: 8px;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
        }

        .cart-title {
            font-size: 24px;
            margin-bottom: 20px;
            border-bottom: 1px solid #ccc;
            padding-bottom: 10px;
        }

        .cart-item {
            display: flex;
            align-items: center;
            justify-content: space-between;
            padding: 15px 0;
            border-bottom: 1px solid #eee;
        }

        .cart-item img {
            width: 100px;
            height: 100px;
            border-radius: 8px;
            object-fit: cover;
        }

        .cart-item-details {
            flex-grow: 1;
            margin-left: 20px;
        }

        .cart-item-details h3 {
            margin: 0;
            color: #333;
        }

        .cart-item-details p {
            margin: 5px 0;
            color: #555;
        }

        .cart-item-actions {
            display: flex;
            align-items: center;
            gap: 10px;
        }

        .cart-item-actions input[type="number"] {
            width: 50px;
            text-align: center;
            padding: 5px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }

        .cart-item-actions button {
            padding: 8px 12px;
            border: none;
            background-color: #FF6347;
            color: white;
            border-radius: 5px;
            cursor: pointer;
            transition: all 0.3s ease;
        }

        .cart-item-actions button:hover {
            background-color: #E5533D;
        }

        .cart-summary {
            margin-top: 20px;
            text-align: right;
            font-size: 18px;
        }

        .cart-summary button {
            padding: 10px 20px;
            background-color: green;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            transition: all 0.3s ease;
        }

        .cart-summary button:hover {
            background-color: darkgreen;
        }

        footer {
            margin-top: auto;
            text-align: center;
            background-color: #333;
            color: white;
            padding: 10px 0;
            font-size: 14px;
        }

        .cart-actions {
            display: flex;
            justify-content: flex-start; /* Aligns both buttons to the left */
            gap: 20px;
            margin-top: 20px;
        }

        .cart-actions a {
            text-decoration: none;
        }

        .back-btn, .checkout-btn {
            padding: 12px 25px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-size: 16px;
            transition: all 0.3s ease;
        }

        .back-btn {
            background-color: #FF6347;
            color: white;
        }

        .back-btn:hover {
            background-color: #E5533D;
        }

        .checkout-btn {
            background-color: #28a745;
            color: white;
        }

        .checkout-btn:hover {
            background-color: #218838;
        }

        /* Center alignment for cart-summary content */
        .cart-summary {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-top: 20px;
            font-size: 18px;
        }

        .cart-summary button {
            margin-left: 20px;
        }
    </style>
</head>
<body>
<header>
    <div class="logo"></div>
    <nav>
        <a href="home.jsp">Home</a>
        <a href="CartServlet?act=clearcart">Clear Cart</a>
        <a href="checkout.jsp">Proceed to Checkout</a>
    </nav>
</header>
    
<div class="cart-container">
    <h2 class="cart-title">Your Shopping Cart</h2>
    <%
    CartDaoImplementation cart = (CartDaoImplementation) session.getAttribute("cart");

    double grandTotal = 0.0;
    

    if (cart != null && !cart.getItems().isEmpty()) 
    {
        for (CartItem item : cart.getItems().values())
        {
            double subTotal = item.getQuantity() * item.getPrice();
            grandTotal += subTotal;
    %>
    <div class="cart-item">
        <div class="cart-item-details">
            <h3><%= item.getName() %></h3>
            <p>Price: ₹<%= String.format("%.2f", item.getPrice()) %></p>
            <p>SubTotal: ₹<%= String.format("%.2f", item.getSubTotal()) %></p>
        </div>
        <div class="cart-item-actions">
            <form action="CartServlet" method="post">
                <input type="hidden" name="act" value="update"> 
                <input type="hidden" name="MenuId" value="<%= item.getMenuId() %>">
                <input type="number" name="quantity" value="<%= item.getQuantity() %>" min="1">
                <button type="submit">Update</button>
            </form>
            <form action="CartServlet" method="post">
                <input type="hidden" name="act" value="remove"> 
                <input type="hidden" name="MenuId" value="<%= item.getMenuId() %>">
                <button type="submit">Remove</button>
            </form>
        </div>
    </div>
    <%
        }
    } else {
    %>
    <p>Your cart is empty!</p>
    <%
    }
    %>

    <!-- Buttons to Go Back and Proceed to Checkout -->
    <div class="cart-actions">
        <a href="menu.jsp"><button class="back-btn">Add more items</button></a>
        <a href="checkout.jsp"><button class="checkout-btn">Proceed to Checkout</button></a>
    </div>

    <div class="cart-summary">
        <p>Total Amount: ₹<%= String.format("%.2f", grandTotal) %></p>
    </div>

</div>

<footer>
    <p>&copy; 2024 Your Shop. All Rights Reserved.</p>
</footer>
</body>
</html>
