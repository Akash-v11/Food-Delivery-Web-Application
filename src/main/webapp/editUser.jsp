<%@page import="model.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit User Details</title>
<style>
    body {
        font-family: 'Poppins', sans-serif;
        margin: 0;
        padding: 0;
        background-color: #f7f7f7;
        color: #333;
    }

    .container {
        max-width: 800px;
        margin: 50px auto;
        background-color: white;
        padding: 20px;
        border-radius: 8px;
        box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
    }

    .section {
        margin-bottom: 20px;
    }

    .section h1 {
        text-align: center;
    }

    .info-item {
        margin: 15px 0;
    }

    .info-item span {
        font-weight: bold;
    }

    .info-item input {
        width: 100%;
        padding: 8px;
        margin-top: 5px;
        border-radius: 5px;
        border: 1px solid #ccc;
    }

    .links {
        text-align: center;
        margin-top: 30px;
    }

    .links button {
        background-color: #fc8019;
        color: white;
        padding: 10px 20px;
        border-radius: 5px;
        border: none;
        cursor: pointer;
        font-size: 16px;
    }

    .links button:hover {
        background-color: #ff9e4e;
    }

</style>
</head>
<body>
 <%
    User user = (User) session.getAttribute("LoggedInUser");
%>
<div class="container">
    <h1>Edit User Details</h1>
    <form action="UpdateUser" method="post">
        <div class="info">
            <div class="info-item"><span>User ID:</span><input type="text" name="uid" value="<%= user.getUid() %>" readonly></div>
            <div class="info-item"><span>Name:</span><input type="text" name="username" value="<%= user.getUsername() %>" required></div>
            <div class="info-item"><span>Email:</span><input type="email" name="email" value="<%= user.getEmail() %>" required></div>
            <div class="info-item"><span>Password:</span><input type="password" name="password" value="<%= user.getPassword() %>" required></div>
            <div class="info-item"><span>Mobile:</span><input type="text" name="mobile"  value="<%= user.getMobile() %>" maxlength="10" required></div>
        </div>
        <div class="links">
            <button type="submit">Update Profile</button>
            <a href="userdetails.jsp"><button type="button">Go Back</button></a>
        </div>
    </form>
</div>
 
</body>
</html>
 