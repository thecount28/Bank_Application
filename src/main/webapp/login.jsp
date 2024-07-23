<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>SecureBank Login</title>
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@300;400;500&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }
        body {
            font-family: 'Roboto', sans-serif;
            background: linear-gradient(135deg, #f5f7fa 0%, #c3cfe2 100%);
            height: 100vh;
            display: flex;
            align-items: center;
            justify-content: center;
        }
        .login-container {
            background-color: #ffffff;
            border-radius: 10px;
            box-shadow: 0 14px 28px rgba(0,0,0,0.25), 0 10px 10px rgba(0,0,0,0.22);
            width: 400px;
            overflow: hidden;
        }
        .header {
            background-color: #0077be;
            color: white;
            text-align: center;
            padding: 20px;
        }
        .header h1 {
            font-size: 24px;
            font-weight: 500;
        }
        .toggle-container {
            display: flex;
            justify-content: center;
            margin: 20px 0;
        }
        .toggle-btn {
            background-color: #f0f0f0;
            border: none;
            color: #333;
            padding: 10px 20px;
            cursor: pointer;
            transition: background-color 0.3s;
        }
        .toggle-btn.active {
            background-color: #0077be;
            color: white;
        }
        .form-container {
            padding: 20px 40px;
        }
        .input-group {
            margin-bottom: 20px;
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
        .forgot-password {
            text-align: center;
            margin-top: 20px;
        }
        .forgot-password a {
            color: #0077be;
            text-decoration: none;
        }
    </style>
</head>
<body>
    <div class="login-container">
        <div class="header">
            <h1>SecureBank Login</h1>
        </div>
        <div class="toggle-container">
            <button id="customerToggle" class="toggle-btn active">Customer</button>
            <button id="adminToggle" class="toggle-btn">Admin</button>
        </div>
        <div class="form-container">
            <form id="customerForm" action="CustomerLoginServlet" method="post" onsubmit="return validateLogin('customer')" style="display: block;">
                <div class="input-group">
                    <input type="text" name="username" placeholder="Account Number" required>
                </div>
                <div class="input-group">
                    <input type="password" name="password" placeholder="Password" required>
                </div>
                <button type="submit" class="submit-btn">Customer Login</button>
            </form>
            <form id="adminForm" action="AdminLoginServlet" method="post" onsubmit="return validateLogin('admin')" style="display: none;">
                <div class="input-group">
                    <input type="text" name="username" placeholder="Username" required>
                </div>
                <div class="input-group">
                    <input type="password" name="password" placeholder="Password" required>
                </div>
                <button type="submit" class="submit-btn">Admin Login</button>
            </form>
            <div class="forgot-password">
                <a href="#">Forgot Password?</a>
            </div>
        </div>
    </div>

    <script>
        const customerToggle = document.getElementById('customerToggle');
        const adminToggle = document.getElementById('adminToggle');
        const customerForm = document.getElementById('customerForm');
        const adminForm = document.getElementById('adminForm');

        customerToggle.addEventListener('click', () => {
            customerToggle.classList.add('active');
            adminToggle.classList.remove('active');
            customerForm.style.display = 'block';
            adminForm.style.display = 'none';
        });

        adminToggle.addEventListener('click', () => {
            adminToggle.classList.add('active');
            customerToggle.classList.remove('active');
            adminForm.style.display = 'block';
            customerForm.style.display = 'none';
        });

        function validateLogin(type) {
            const form = type === 'admin' ? adminForm : customerForm;
            const username = form.querySelector('input[name="username"]').value;
            const password = form.querySelector('input[name="password"]').value;

            if (username.trim() === '' || password.trim() === '') {
                alert('Username and Password must be filled out.');
                return false;
            }
            return true;
        }
    </script>
</body>
</html>