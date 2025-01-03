<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Order Failure</title>
    <link rel="stylesheet" href="styles.css">
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f8d7da;
            color: #721c24;
            text-align: center;
            margin-top: 50px;
        }
        .container {
            max-width: 600px;
            padding: 20px;
            border: 1px solid #f5c6cb;
            background-color: #f8d7da;
            border-radius: 8px;
            box-shadow: 0px 0px 15px rgba(0, 0, 0, 0.1);
        }
        h1 {
            font-size: 24px;
            margin-bottom: 10px;
        }
        p {
            font-size: 16px;
            margin: 15px 0;
        }
        .error-message {
            font-weight: bold;
        }
        a {
            display: inline-block;
            margin-top: 20px;
            padding: 10px 20px;
            background-color: #155724;
            color: #fff;
            text-decoration: none;
            border-radius: 5px;
        }
        a:hover {
            background-color: #0c3c10;
        }
    </style>
</head>
<body>

    <div class="container">
        <h1>Order Processing Failed</h1>
        <p>Unfortunately, your order could not be processed due to an error.</p>
        
        <p class="error-message">Error Message: 
            <%= request.getParameter("message") != null ? request.getParameter("message") : "Unknown error occurred" %>
        </p>

        <p>If you believe this is a mistake, please contact customer support or try again later.</p>

        <a href="home.jsp">Go Back to Homepage</a>
    </div>

</body>
</html>
