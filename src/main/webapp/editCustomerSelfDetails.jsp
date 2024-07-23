<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.banking.model.Customer" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Calendar" %>
<%
    Customer customer = (Customer) session.getAttribute("customer");
    if (customer == null) {
        response.sendRedirect("customerLogin.jsp?error=Please login first");
        return;
    }

    // Calculate the minimum date for 18 years old
    Calendar cal = Calendar.getInstance();
    cal.add(Calendar.YEAR, -18);
    String minDate = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Edit Customer Details - SecureBank</title>
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
            height: 100vh;
            display: flex;
            align-items: center;
            justify-content: center;
        }
        .form-container {
            background-color: #ffffff;
            border-radius: 10px;
            box-shadow: 0 14px 28px rgba(0,0,0,0.25), 0 10px 10px rgba(0,0,0,0.22);
            width: 600px;
            overflow: hidden;
            padding: 20px 40px;
        }
        .form-container h2 {
            text-align: center;
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
        <h2>Edit Customer Details</h2>
        <form id="editForm" action="CustomerSelfEditServlet" method="post">
            <div class="input-group">
                <label for="fullName">Full Name:</label>
                <input type="text" id="fullName" name="fullName" value="<%= customer.getFullName() %>" required>
            </div>
            <div class="input-group">
                <label for="dob">Date of Birth:</label>
                <input type="date" id="dob" name="dob" value="<%= new SimpleDateFormat("yyyy-MM-dd").format(customer.getDateOfBirth()) %>" required>
                <div id="dobError" class="error-message"></div>
            </div>
            <div class="input-group">
                <label for="emailId">Email:</label>
                <input type="email" id="emailId" name="emailId" value="<%= customer.getEmailId() %>" required>
            </div>
            <div class="input-group">
                <label for="mobileNo">Mobile:</label>
                <input type="text" id="mobileNo" name="mobileNo" value="<%= customer.getMobileNo() %>" required pattern="^\d{10}$">
                <div id="mobileError" class="error-message"></div>
            </div>
            <div class="input-group">
            <label for="address">Address:</label>
            <input type="text" id="address" name="address" value="<%= customer.getAddress() %>" required>
            <div id="addressError" class="error-message"></div>
            </div> 
            <button type="submit" class="submit-btn">Update Details</button>
        </form>
    </div>

    <script>
        document.getElementById('editForm').addEventListener('submit', function(event) {
            let valid = true;

            // Validate Date of Birth
            const dob = document.getElementById('dob').value;
            const dobError = document.getElementById('dobError');
            const minDate = new Date('<%= minDate %>');
            const userDob = new Date(dob);
            if (userDob > minDate) {
                dobError.textContent = 'You must be at least 18 years old.';
                valid = false;
            } else {
                dobError.textContent = '';
            }

            // Validate Mobile Number
            const mobileNo = document.getElementById('mobileNo').value;
            const mobileError = document.getElementById('mobileError');
            const mobilePattern = /^\d{10}$/;
            if (!mobilePattern.test(mobileNo)) {
                mobileError.textContent = 'Mobile number must be a 10-digit number.';
                valid = false;
            } else {
                mobileError.textContent = '';
            }

            if (!valid) {
                event.preventDefault();
            }
        });
    </script>
</body>
</html>
