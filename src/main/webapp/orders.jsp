<%@page import="model.User"%>
<%@page import="model.Orders"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <% User user = (User) session.getAttribute("LoggedInUser");%>
    <%
    	List<Orders> ordersList = (List<Orders>) session.getAttribute("fetchedorders"); 
		System.out.println("\nfetched in ordersList.jsp " + ordersList + "\n\n");
		
	      Orders orders;

    %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Orders</title>
   <style>
        /* Global Styles */
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f9;
            display: flex;
            flex-direction: column;
            align-items: center;
        }

        /* Header Styling */
        header {
            width: 100%;
        background: linear-gradient(to right, #fc8019, #f56c3a); /* Vibrant gradient */
            color: white;
            padding: 20px;
            text-align: center;
            font-size: 2em;
        }

        /* Table Styling */
        table {
            margin: 20px 0;
            width: 90%;
            border-collapse: collapse;
            background-color: white;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            border-radius: 8px;
            overflow: hidden;
        }

        th, td {
            text-align: left;
            padding: 15px;
            border: 1px solid #ddd;
        }

        th {
            background-color: #ff6600; /* Swiggy Orange */
            color: white;
            text-transform: uppercase;
            font-size: 1em;
        }

        tr:nth-child(even) {
            background-color: #f9f9f9;
        }

        tr:hover {
            background-color: #ffebcc; /* Light orange hover */
        }

        .no-data {
            text-align: center;
            margin: 50px;
            font-size: 1.2em;
            color: #555;
        }

        /* Footer Styling */
        footer {
            width: 100%;
       		 background: linear-gradient(to right, #fc8019, #f56c3a); /* Vibrant gradient */
            color: white;
            text-align: center;
            padding: 15px;
         }

        /* Dropdown Button */
        .dropdown {
            position: absolute;
            top: 20px;
            right: 20px;
        }

        .dropdown-button {
            background-color: #ff6600;
            color: white;
            border: none;
            padding: 10px 20px;
            font-size: 1em;
            cursor: pointer;
            border-radius: 5px;
        }

        .dropdown-content {
            display: none;
            position: absolute;
            right: 0;
            top: 40px;
            background-color: white;
            min-width: 160px;
            box-shadow: 0 8px 16px rgba(0, 0, 0, 0.2);
            border-radius: 5px;
        }

        .dropdown:hover .dropdown-content {
            display: block;
        }

        .dropdown-content a {
            color: black;
            padding: 12px 16px;
            text-decoration: none;
            display: block;
        }

        .dropdown-content a:hover {
            background-color: #ff4500; /* Darker orange */
            color: white;
        }

        /* Responsive Styles */
        @media (max-width: 768px) {
            header {
                font-size: 1.5em;
                padding: 15px;
            }

            table {
                width: 100%;
            }
        }
    </style>
</head>

<body>
    <!-- Dropdown Menu for Navigation -->
    <div class="dropdown">
        <button class="dropdown-button">View More</button>
        <div class="dropdown-content">
             <a href="home.jsp"><button>Home</button></a>
            <a href="ViewOrders?uid=<%= user.getUid() %>"><button>Orders</button> </a>
            <a href="ViewOrdersItem?uid=<%=   %>"><button>Orders Items</button> </a>
            <a href="ViewOrdersHistory?uid=<%= user.getUid() %>"><button>Orders History</button> </a>
            <a href="logoutservlet"><button>Logout</button></a>
        </div>
    </div>

    <!-- Header Section -->
    <header>
        Orders  
    </header>
     <!-- Main Content Section -->
    <main>
        <div class="content-container">
            <% if (ordersList != null && !ordersList.isEmpty()) { %>
            <table>
                <thead>
                    <tr>
                        <th>Orders ID</th>
                        <th>User ID</th>
                        <th>Restaurant ID</th>
                        <th>Menu ID</th>
                        <th>Quantity</th>
                        <th>Total Amount</th>
                        <th>PaymentType</th>
                        <th>Date And Time</th>
                        <th>Status</th>
                    </tr>
                </thead>
                <tbody>
                    <% for (Orders order : ordersList)
                    { %>
                    <tr>
                         <td><%= order.getOrdersId() %></td>
                        <td><%= order.getUid() %></td>
                        <td><%= order.getRestaurantid() %></td>
                        <td><%= order.getMenuid() %></td>
                        <td><%= order.getQuantity() %></td>
                        <td><%= order.getTotalAmount() %></td>
                        <td><%= order.getPaymentType() %></td>
                        <td><%= order.getDateAndtime()%></td>                                            
                        <td><%= order.getStatus() %></td>
                    </tr>
                    <% } %>
                </tbody>
            </table>
            <% } else { %>
            <p class="no-data">No order found for the user.</p>
            <% } %>
        </div>
    </main>

    <!-- Footer Section -->
    <footer>
        &copy; <%= java.time.Year.now() %> Your Food. All Rights Reserved.
    </footer>
</body>
</html>
    