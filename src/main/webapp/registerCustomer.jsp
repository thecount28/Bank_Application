<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Register Customer</title>
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@300;400;500&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
    <link rel="stylesheet" href="styles.css">
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }
        body {
            font-family: 'Roboto', sans-serif;
            background: linear-gradient(135deg, #f5f7fa 0%, #c3cfe2 100%);
            min-height: 100vh;
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
            padding: 20px;
            margin: 0;
        }
        .form-container {
            background-color: #ffffff;
            border-radius: 10px;
            box-shadow: 0 14px 28px rgba(0,0,0,0.25), 0 10px 10px rgba(0,0,0,0.22);
            width: 100%;
            max-width: 600px;
            max-height: 90vh; /* Set maximum height */
            overflow-y: auto; /* Enable vertical scrolling */
            padding: 20px 40px;
            margin: 20px auto;
            position: relative; /* Ensure sticky positioning works */
        }
        .form-container .header-container {
            position: sticky;
            top: 0;
            background-color: #ffffff;
            z-index: 1;
            padding: 20px 0; /* Add padding to ensure proper spacing */
        }
        .back-link {
            display: block;
            font-size: 16px;
            color: #0077be;
            text-decoration: none;
            font-weight: 500;
            margin-bottom: 10px; /* Add some space below */
        }
        .back-link:hover {
            text-decoration: underline;
        }
        .form-container h2 {
            text-align: center;
            margin-bottom: 30px;
        }
        .input-group {
            margin-bottom: 20px;
        }
        .input-group label {
            display: block;
            margin-bottom: 5px;
            font-weight: 500;
        }
        .input-group input, .input-group select {
            width: 100%;
            padding: 10px;
            border: 1px solid #ddd;
            border-radius: 5px;
            font-size: 16px;
        }
        .input-group input:focus, .input-group select:focus {
            outline: none;
            border-color: #0077be;
        }
        .error-message {
            color: red;
            font-size: 14px;
            margin-top: 5px;
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
    </style>
</head>
<body>
    <div class="form-container">
        <div class="header-container">
            <a href="adminDashboard.jsp" class="back-link">&larr; Back to Dashboard</a>
            <h2>Register New Customer</h2>
        </div>
        <form name="registerCustomerForm" action="RegisterCustomerServlet" method="post" onsubmit="return validateRegisterCustomerForm();">
            <div class="input-group">
                <label for="fullName">Full Name:</label>
                <input type="text" id="fullName" name="fullName" required>
            </div>
            <div class="input-group">
                <label for="address">Address:</label>
                <input type="text" id="address" name="address" required>
            </div>
            <div class="input-group">
                <label for="mobileNo">Mobile Number:</label>
                <input type="text" id="mobileNo" name="mobileNo" required>
            </div>
            <div class="input-group">
                <label for="emailId">Email:</label>
                <input type="email" id="emailId" name="emailId" required>
            </div>
            <div class="input-group">
                <label for="accountType">Account Type:</label>
                <select id="accountType" name="accountType">
                    <option value="Saving">Saving</option>
                    <option value="Current">Current</option>
                </select>
            </div>
            <div class="input-group">
                <label for="balance">Initial Balance:</label>
                <input type="number" id="balance" name="balance" required>
            </div>
            <div class="input-group">
                <label for="dateOfBirth">Date of Birth:</label>
                <input type="date" id="dateOfBirth" name="dateOfBirth" required>
            </div>
            <div class="input-group">
                <label for="idProof">ID Proof:</label>
                <input type="text" id="idProof" name="idProof" required>
            </div>
            <button type="submit" class="submit-btn">Register</button>
        </form>
    </div>
</body>
</html>
