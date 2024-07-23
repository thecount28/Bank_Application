<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Withdraw Money - SecureBank</title>
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
        function validateWithdraw() {
            const amount = document.forms["withdrawForm"]["amount"].value;

            if (amount <= 0) {
                alert("Withdrawal amount must be greater than zero.");
                return false;
            }
            return true;
        }
    </script>
</head>
<body>
    <div class="form-container">
        <h2>Withdraw Money</h2>
        <form name="withdrawForm" action="WithdrawServlet" method="post" onsubmit="return validateWithdraw()">
            <div class="input-group">
                <input type="number" step="0.01" name="amount" placeholder="Amount" required>
            </div>
            <button type="submit" class="submit-btn">Withdraw</button>
            <div class="links">
                <a href="CustomerDashboardServlet"><i class="fas fa-arrow-left"></i> Back to Dashboard</a>
            </div>
        </form>
    </div>
</body>
</html>
