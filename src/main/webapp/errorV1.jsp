<%--
  Created by IntelliJ IDEA.
  User: moham
  Date: 25/12/2023
  Time: 22:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Error Page</title>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
            display: flex;
            align-items: center;
            justify-content: center;
            height: 100vh;
        }

        .error-container {
            text-align: center;
            max-width: 400px;
        }

        .error-code {
            font-size: 4em;
            color: #e74c3c;
            margin-bottom: 10px;
        }

        .error-message {
            font-size: 1.5em;
            color: #555;
        }

        .back-link {
            display: block;
            margin-top: 20px;
            color: #3498db;
            text-decoration: none;
            font-weight: bold;
        }
    </style>
</head>

<body>
<div class="error-container">
    <div class="error-code">Opps</div>
    <div class="error-message">there is no room available, try to chose another period OR category room</div>
    <a href="index.jsp" class="back-link">Go back</a>
</div>
</body>

</html>
