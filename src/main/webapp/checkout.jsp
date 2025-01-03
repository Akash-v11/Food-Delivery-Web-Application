<%@page import="model.CartItem"%>
<%@page import="daoImplementation.CartDaoImplementation"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
 

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Checkout</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
    <style>
        /* Global Styles */
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background-color: #f4f4f9;
            margin: 0;
            padding: 0;
        }

        /* Header */
        header {
            background-color: #fc8019;
            color: white;
            padding: 15px 20px;
            display: flex;
            justify-content: space-between;
            align-items: center;
        }

        header a {
            text-decoration: none;
            color: white;
            margin: 0 10px;
            font-weight: bold;
        }

        header a:hover {
            text-decoration: underline;
        }

        /* Main container */
        .main-container {
            max-width: 1200px;
            margin: 20px auto;
            padding: 20px;
        }

        /* Card layout */
        .card {
            background: white;
            border-radius: 8px;
            box-shadow: 0px 4px 6px rgba(0, 0, 0, 0.1);
            padding: 20px;
            margin-bottom: 20px;
        }

        .card h3 {
            margin-bottom: 20px;
            font-size: 20px;
            border-bottom: 2px solid #fc8019;
            display: inline-block;
        }

        /* Checkout Form */
        .checkout-form label {
            display: block;
            margin: 10px 0 5px;
            font-weight: bold;
        }

        .checkout-form input, .checkout-form select, .checkout-form button {
            width: 100%;
            padding: 10px;
            margin-bottom: 15px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }

        .checkout-form button {
            background-color: #fc8019;
            color: white;
            font-weight: bold;
            cursor: pointer;
        }

        .checkout-form button:hover {
            background-color: #e67117;
        }

        /* Footer */
        footer {
            background: #333;
            color: #fff;
            text-align: center;
            padding: 10px;
            position: relative;
        }

        footer a {
            color: #fc8019;
            text-decoration: none;
        }

        footer a:hover {
            text-decoration: underline;
        }

        /* Cart Items */
        .cart-item {
            display: flex;
            justify-content: space-between;
            margin-bottom: 10px;
            border-bottom: 1px solid #e0e0e0;
            padding-bottom: 8px;
        }
    </style>
</head>
<body>
    <!-- Header -->
    <header>
        <div class="logo"> </div>
        <nav>
            <a href="home.jsp">Home</a>
            <a href="menu.jsp">Menu</a>
            <a href="cart.jsp">Cart</a>
            <a href="logoutservlet">Logout</a>
        </nav>
    </header>

    <!-- Main Content -->
    <div class="main-container">

        <!-- Cart Items Card -->
        <div class="card">
            <h3>Items in Cart</h3>
            <% 
                int totalItems = 0;
                double grandTotal = 0.0;
                CartDaoImplementation cart = (CartDaoImplementation) session.getAttribute("cart");
                if (cart != null && !cart.getItems().isEmpty()) {
                    for (CartItem item : cart.getItems().values()) {
                        totalItems += item.getQuantity();
                        grandTotal += item.getPrice() * item.getQuantity();
                        System.out.println("fetched quantity: "+item.getQuantity());
            %>
            <div class="cart-item">
                <span><%= item.getName() %></span>
                <span>₹<%= String.format("%.2f", item.getPrice()) %> × <%= item.getQuantity() %></span>
            </div>
            <% 
                    }
                } else { 
            %>
            <div class="cart-item">
                <span>Your cart is empty!</span>
            </div>
            <% } %>
            <% if (cart != null && !cart.getItems().isEmpty()) { %>
            <div class="cart-item">
                <strong>Total Items:</strong>
                <span><%= totalItems %></span>
            </div>
            <div class="cart-item">
                <strong>Grand Total:</strong>
                <span>₹<%= String.format("%.2f", grandTotal) %></span>
            </div>
            <% } %>
        </div>

        <!-- Checkout Form Card -->
        <div class="card">
            <h3>Checkout</h3>
            <form action="orderservlet" method="post" class="checkout-form">
                <label for="address">Delivery Address</label>
                <input type="text" id="address" name="address" placeholder="Enter your address" required>

                <label for="payment-method">Payment Method</label>
                <select id="payment-method" name="payment-method" required>
                    <option value="Cash on Delivery">Cash on Delivery</option>
                    <option value="UPI">UPI</option>
                    <option value="Card">Credit/Debit Card</option>
                </select>

                <input type="hidden" name="quantity" value="<%= totalItems  %>">
                <input type="hidden" name="grandTotal" value="<%= grandTotal %>">
                <button type="submit">Place Order</button>
            </form>
        </div>
    </div>

    <!-- Footer -->
    <footer>
        &copy; 2024 My E-Commerce Site | <a href="terms.jsp">Terms</a> | <a href="privacy-policy.jsp">Privacy Policy</a>
    </footer>
</body>
</html>
