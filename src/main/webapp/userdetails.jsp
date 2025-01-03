<%@page import="model.OrdersItem"%>
<%@page import="java.util.List"%>
<%@page import="daoImplementation.UserDaoImplementation"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.sql.Timestamp"%>
<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="java.time.LocalDateTime"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="model.User"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>User Details</title>
<style>
    /* General page styling */
    body {
        font-family: 'Poppins', sans-serif;
        background-color: #f9fafb;
        margin: 0;
        padding: 0;
        color: #333;
    }

    /* Container for main content */
    .container {
        max-width: 900px;
        margin: 50px auto;
        padding: 20px;
        background-color: #ffffff;
        border-radius: 12px;
        box-shadow: 0 6px 20px rgba(0, 0, 0, 0.1);
        transition: transform 0.3s ease;
    }

    .container:hover {
        transform: scale(1.02);
    }

    /* Heading styling */
    h1 {
        text-align: center;
        color: #fc8019;
        font-size: 30px;
        margin-bottom: 20px;
        font-weight: 600;
    }

    /* Information section layout */
    .info {
        display: grid;
        grid-template-columns: 1fr 2fr;
        gap: 20px;
        margin-bottom: 30px;
    }

    .info-item {
        padding: 15px;
        background-color: #ffffff;
        border: 1px solid #ddd;
        border-radius: 8px;
        transition: background-color 0.3s ease;
    }

    .info-item:hover {
        background-color: #f1f1f1;
    }

    .info-item span {
        font-weight: bold;
        color: #fc8019;
    }

    .info-item p {
        margin-top: 8px;
        color: #333;
        font-size: 16px;
    }

    /* Button styling */
    .links {
        text-align: center;
        margin-top: 20px;
    }

    .links button {
        background-color: #fc8019;
        color: white;
        padding: 12px 25px;
        border: none;
        border-radius: 8px;
        cursor: pointer;
        font-size: 18px;
        font-weight: bold;
        transition: background-color 0.3s ease, transform 0.2s ease;
        margin: 10px;
    }

    .links button:hover {
        background-color: #ff9e4e;
        transform: translateY(-3px);
    }

    .links a {
        text-decoration: none;
    }

    /* Header styles for alignment and color */
    /* Header styles */
/* Header styles */
header {
        background: linear-gradient(to right, #fc8019, #f56c3a); /* Vibrant gradient */
    padding: 10px;
    text-align: right;
    color: white;
    font-size: 16px;
    font-weight: bold;
}

header nav {
    display: inline-block;
}

header nav a {
    text-decoration: none;
    margin: 0 10px;
}

header nav button {
    background-color: white;  /* White button background for contrast */
    color: #fc8019;  /* Orange text to match header */
    border: none;
    padding: 8px 16px;
    border-radius: 5px;
    cursor: pointer;
    font-size: 16px;
    font-weight: bold;
    transition: background-color 0.3s ease, transform 0.3s ease, box-shadow 0.3s ease;
}

header nav button:hover {
    background-color: #ffe0b2; /* Light orange on hover */
    transform: translateY(-3px); /* Slight lift on hover */
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2); /* Add shadow effect */
}



    /* Footer styling */
    footer {
        text-align: center;
        padding: 10px;
        background: linear-gradient(to right, #fc8019, #f56c3a); /* Vibrant gradient */
        color: white;
        font-size: 14px;
        margin-top: 30px;
        border-top: 4px solid #fc8019;
    }

    /* Responsive design for small screens */
    @media (max-width: 768px) {
        h1 {
            font-size: 24px;
        }

        .info {
            grid-template-columns: 1fr;
        }

        .info-item {
            padding: 12px;
        }

        .links button {
            width: 100%;
            padding: 14px;
        }
    }
</style>
</head>
<body>
    <% 
	    User user = (User) session.getAttribute("LoggedInUser");
 	 %> 
	 
	 
 
    <header>
        <nav>
            <a href="home.jsp"><button>Home</button></a>
            <a href="ViewOrders?uid=<%= user.getUid() %>"><button>Orders</button> </a>
            <a href="ViewOrdersHistory?uid=<%= user.getUid() %>"><button>Orders History</button> </a>
            <a href="logoutservlet"><button>Logout</button></a>
        </nav>
    </header>
    
 
    <% 
        if (user != null) {
            // Get current timestamp
            LocalDateTime now = LocalDateTime.now();
            Timestamp lastLogin = Timestamp.valueOf(now);

            // Update the last login in the database
            UserDaoImplementation userDao = new UserDaoImplementation();
            userDao.updateLastLogin(user.getUid());  // Update last login

            // Re-fetch the updated user data (including the updated 'lastlogin' field)
            user = userDao.fetch_ID(user.getUid());  // Assuming this fetches all details of the user
            session.setAttribute("LoggedInUser", user);  // Update session with the latest data
        } 
    %>
    <div class="container">
        <h1>User Details</h1>
        <div class="info">
            <div class="info-item"><span>User ID:</span><p><%= user.getUid() %></p></div>
            <div class="info-item"><span>Name:</span><p><%= user.getUsername() %></p></div>
            <div class="info-item"><span>Email:</span><p><%= user.getEmail() %></p></div>
            <div class="info-item"><span>Password:</span><p><%= user.getPassword() %></p></div>
            <div class="info-item"><span>Mobile:</span><p><%= user.getMobile() %></p></div>
            <div class="info-item"><span>Account Created:</span><p><%= user.getAccCreated() %></p></div>
            <div class="info-item"><span>Last Login:</span><p><%= user.getLastlogin() %></p></div>
        </div>
        <div class="links">
            <a href="FetchOne?uid=<%= user.getUid() %>"><button>Edit User Details</button></a>
            <a href="home.jsp"><button>Go Back</button></a>
        </div>
    </div>
</body>

<footer>
    &copy; 2024 TAP Eats. All Rights Reserved.
</footer>

</html>
