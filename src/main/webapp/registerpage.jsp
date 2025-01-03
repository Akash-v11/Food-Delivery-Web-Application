<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Register Page</title>
<link rel="stylesheet" href="styles.css">
</head>
<body>
<body>

	<div class="form-container">
		<h2>Register</h2>

		<form action="Validation" method="POST">

			<div class="form-group">
				<label for="name">Full Name:</label>
				<input type="text" id="name" name="name" placeholder="Enter your name" required maxlength="20">
			</div>
			<div class="form-group">
				<label for="email">Email:</label>
				<input type="email" id="email" name="email" placeholder="Enter your email" required maxlength="30">
			</div>
			<div class="form-group">
				<label for="mobile">Mobile:</label>
				<input type="tel" id="mobile" name="mobile" placeholder="Enter your mobile" required maxlength="10">
			</div>
			<div class="form-group">
				<label for="password">Password:</label>
				<input type="password" id="password" name="password" placeholder="Create a password" required
					maxlength="8">
			</div>
			<div class="form-group">
				<label for="confirmPassword">Confirm Password:</label>
				<input type="password" id="confirmPassword" name="confirmPassword" placeholder="Confirm your password"
					required maxlength="8">
			</div>
			 
			<button type="submit">Register</button>
		</form>
		<p>Already have an account? <a href="loginpage.jsp">Login here</a></p>
	</div>

</body>
</body>
</html>