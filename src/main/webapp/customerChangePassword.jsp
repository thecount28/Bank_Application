<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Change Password - SecureBank</title>
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@300;400;500&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
    <link rel="stylesheet" href="styles.css">
    <style>
        body {
            font-family: 'Roboto', sans-serif;
            background: linear-gradient(135deg, #f5f7fa 0%, #c3cfe2 100%);
            height: 100vh;
            margin: 0;
            padding: 20px;
            display: flex;
            flex-direction: column;
            align-items: center;
        }
        .form-container {
            background-color: #ffffff;
            border-radius: 10px;
            box-shadow: 0 14px 28px rgba(0,0,0,0.25), 0 10px 10px rgba(0,0,0,0.22);
            width: 100%;
            max-width: 500px;
            padding: 20px;
            text-align: center;
        }
        h2 {
            margin-bottom: 20px;
        }
        .input-group {
            margin-bottom: 20px;
        }
        .input-group label {
            display: block;
            margin-bottom: 5px;
            font-weight: 500;
        }
        .input-group input {
            width: 100%;
            padding: 10px;
            border: 1px solid #ddd;
            border-radius: 5px;
            font-size: 16px;
        }
        .input-group input:focus {
            outline: none;
            border-color: #0077be;
        }
        .error-message, .success-message {
            margin-bottom: 15px;
            font-size: 14px;
        }
        .error-message {
            color: red;
        }
        .success-message {
            color: green;
        }
        .submit-btn {
            width: 100%;
            padding: 12px;
            background-color: #0077be;
            color: white;
            border: none;
            border-radius: 5px;
            font-size: 16px;
            cursor: pointer;
            transition: background-color 0.3s;
        }
        .submit-btn:hover {
            background-color: #005fa3;
        }
        .links {
            margin-top: 20px;
        }
        .links a {
            text-decoration: none;
            color: #0077be;
            font-weight: 500;
            transition: color 0.3s;
        }
        .links a:hover {
            color: #005fa3;
        }
    </style>
    <script>
        function validateForm() {
            var newPassword = document.forms["changePasswordForm"]["newPassword"].value;
            var confirmPassword = document.forms["changePasswordForm"]["confirmPassword"].value;

            if (newPassword !== confirmPassword) {
                alert("New password and confirm password do not match.");
                return false;
            }

            if (newPassword.length < 8) {
                alert("Password must be at least 8 characters long.");
                return false;
            }

            return true;
        }
    </script>
</head>
<body>
    <div class="form-container">
        <h2>Change Password</h2>

        <!-- Display error message -->
        <% 
            String error = (String) request.getAttribute("error");
            if (error != null) { 
        %>
            <div class="error-message"><%= error %></div>
        <% } %>

        <!-- Display success message -->
        <% 
            String success = (String) request.getAttribute("success");
            if (success != null) { 
        %>
            <div class="success-message"><%= success %></div>
        <% } %>

        <form name="changePasswordForm" action="customerChangePasswordServlet" method="post" onsubmit="return validateForm();">
            <div class="input-group">
                <label for="currentPassword">Current Password:</label>
                <input type="password" name="currentPassword" required>
            </div>
            <div class="input-group">
                <label for="newPassword">New Password:</label>
                <input type="password" name="newPassword" required>
            </div>
            <div class="input-group">
                <label for="confirmPassword">Confirm New Password:</label>
                <input type="password" name="confirmPassword" required>
            </div>
            <button type="submit" class="submit-btn">Change Password</button>
            <div class="links">
                <a href="CustomerDashboardServlet"><i class="fas fa-arrow-left"></i> Back to Dashboard</a>
            </div>
        </form>
    </div>
</body>
</html>
