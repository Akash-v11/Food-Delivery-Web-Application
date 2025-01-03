<%@page import="model.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Order Confirmation</title>
    <meta name="description" content="Your order has been confirmed!">
    <style>
        /* Reset */
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        /* Body & Fonts */
        body {
            font-family: 'Roboto', sans-serif;
            background-color: #f7f9fc;
            color: #2c3e50;
            display: flex;
            flex-direction: column;
            min-height: 100vh;
        }

        /* Header */
        header {
             background: linear-gradient(to right, #fc8019, #f56c3a);
            color: white;
            padding: 20px 10%;
            text-align: center;
            font-size: 1.2rem;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }

        header h1 {
            font-size: 2rem;
            font-weight: bold;
            margin: 0;
        }

        header p {
            margin: 5px 0 0;
            font-size: 1rem;
        }

        /* Main Content */
        .container {
            flex: 1;
            padding: 40px 10%;
            display: flex;
            flex-direction: column;
            align-items: center;
        }

        .card {
            background-color: white;
            border-radius: 12px;
            box-shadow: 0 6px 12px rgba(0, 0, 0, 0.1);
            max-width: 700px;
            width: 100%;
            overflow: hidden;
            padding: 20px 30px;
            text-align: left;
            margin: 20px 0;
        }

        .card h3 {
            font-size: 2rem;
            margin-bottom: 15px;
            color: #2c3e50;
        }

        .card p {
            font-size: 1rem;
            color: #34495e;
            line-height: 1.5;
            margin: 10px 0;
        }

        .card strong {
            color: #e74c3c;
        }

        .card p a {
            color: #3498db;
            text-decoration: none;
            font-weight: bold;
        }

        .card p a:hover {
            text-decoration: underline;
        }

        /* Button Styles */
        .button {
            display: inline-block;
            padding: 12px 25px;
            font-size: 1rem;
            background-color: #3498db;
            color: white;
            border-radius: 8px;
            text-decoration: none;
            font-weight: bold;
            text-align: center;
            transition: background-color 0.3s, transform 0.3s;
            margin-top: 20px;
        }

        .button:hover {
            background-color: #2980b9;
            transform: scale(1.05);
        }

        .button:active {
            background-color: #1c5987;
            transform: scale(1);
        }

        /* Footer */
        footer {
             background: linear-gradient(to right, #fc8019, #f56c3a);
            color: white;
            text-align: center;
            padding: 15px 10%;
            font-size: 0.9rem;
            margin-top: auto;
        }

        footer a {
            color: #f39c12;
            text-decoration: none;
        }

        footer a:hover {
            text-decoration: underline;
        }

        /* Responsive Styles */
        @media only screen and (max-width: 768px) {
            .container {
                padding: 20px;
            }

            header h1 {
                font-size: 1.8rem;
            }

            .card h3 {
                font-size: 1.5rem;
            }

            .button {
                font-size: 0.9rem;
                padding: 10px 20px;
            }
        }
    </style>
</head>
<body>
    <!-- Header Section -->
    <header>
        <h1>Order Confirmation</h1>
        <p>Your order is confirmed and being prepared!</p>
    </header>

    <!-- Main Content Section -->
    <div class="container">
        <div class="card">
            <h3>Order Details</h3>
            <%
                User user = (User) session.getAttribute("LoggedInUser"); 
               // int orderId = (int) session.getAttribute("orderId");  
				int orderId = (int) session.getAttribute("orderId");
                String address = (String) session.getAttribute("address");
                String paymentMethod = (String) session.getAttribute("paymentMethod");
                Float grandTotal = (Float) session.getAttribute("grandTotal");

                if (address != null && paymentMethod != null && orderId != 0 && grandTotal != null) {
            %>
                <p><strong>Customer Name:</strong> <%= user.getUsername() %></p>
                <p><strong>Delivery Address:</strong> <%= address %></p>
                <p><strong>Payment Method:</strong> <%= paymentMethod %></p>
                <p><strong>Order ID:</strong> <%= orderId %></p>
                <p><strong>Grand Total:</strong> ₹<%= grandTotal %></p>
            <%
                } else {
            %>
                <p style="color: red;">Unable to confirm the order details. Some information is missing.</p>
                <a href="failure.jsp" class="button">Go back to the order page</a>
            <%
                }
             %>
            <hr>
            <p>Thank you for your order! You will receive a confirmation email shortly.</p>
            <a href="home.jsp" class="button">Return to Home</a>
        </div>
    </div>

    <!-- Footer Section -->
    <footer>
        <p>&copy; 2024 TAP Academy E-Commerce. All Rights Reserved.</p>
        <p>Need help? <a href="contact.jsp">Contact Us</a></p>
    </footer>
    <% System.out.println("Project completed");  %>
</body>
</html>
