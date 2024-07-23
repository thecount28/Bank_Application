<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Customer Login</title>
    <link rel="stylesheet" href="styles.css">
    <script>
        function validateLogin() {
            const username = document.forms["loginForm"]["username"].value;
            const password = document.forms["loginForm"]["password"].value;

            if (username === "" || password === "") {
                alert("Username and Password must be filled out.");
                return false;
            }
            return true;
        }
    </script>
</head>

    
<body>
    <form name="loginForm" action="CustomerLoginServlet" method="post" onsubmit="return validateLogin()">
        <input type="text" name="username" placeholder="Account Number" required><br>
        <input type="password" name="password" placeholder="Password" required><br>
        <button type="submit">Login</button>
    </form>
</body>
</html>
