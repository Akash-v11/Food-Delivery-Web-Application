<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login Page</title>
<link rel="stylesheet" href="styles.css">
</head>
<body>
<div class="form-container">
		<h2>Login</h2>
		<%System.out.println("Project Starts"); %>
		<form action="Login">

			<div class="form-group">
				<label for="email">Email:</label>
				<input type="email" id="email" name="email" placeholder="Enter your email" required maxlength="50">
			</div>

			<div class="form-group">
				<label for="password">Password:</label>
				<input type="password" id="password" name="password" placeholder="Enter your password" required maxlength="8">
			</div>

			<button type="submit" value="Login">Login</button>
		</form>
		<p>Don't have an account? <a href="registerpage.jsp">Register here</a></p>
	</div> 

</body>
</html>