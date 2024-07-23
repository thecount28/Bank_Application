<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="com.banking.model.Customer" %>
<%
    Customer customer = (Customer) request.getAttribute("customer");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>View Customer Details - SecureBank</title>
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@300;400;500&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
    <link rel="stylesheet" href="styles.css">
    <style>
        body {
            font-family: 'Roboto', sans-serif;
            background: linear-gradient(135deg, #f5f7fa 0%, #c3cfe2 100%);
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: flex-start;
            min-height: 100vh;
        }
        .form-container {
            background-color: #ffffff;
            border-radius: 10px;
            box-shadow: 0 14px 28px rgba(0,0,0,0.25), 0 10px 10px rgba(0,0,0,0.22);
            width: 90%;
            max-width: 800px;
            padding: 20px 40px;
            margin: 40px 0;
            overflow-y: auto;
            max-height: calc(100vh - 80px);
        }
        .form-container h2 {
            margin-bottom: 20px;
            color: #333;
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
        .table-container {
            margin-top: 20px;
            width: 100%;
            overflow-x: auto;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-bottom: 20px;
        }
        table th, table td {
            padding: 12px;
            border: 1px solid #ddd;
            text-align: left;
        }
        table th {
            background-color: #f5f5f5;
            font-weight: 500;
        }
        .back-link {
            text-decoration: none;
            color: #0077be;
            font-weight: 500;
        }
        .back-link:hover {
            color: #005fa3;
        }
    </style>
</head>
<body>
    <div class="form-container">
        <h2>View Customer Details</h2>
        
        <form action="ViewCustomerServlet" method="post">
            <div class="input-group">
                <label for="accountNo">Enter Account Number:</label>
                <input type="text" id="accountNo" name="accountNo" placeholder="Account Number" required>
            </div>
            <button type="submit" class="submit-btn">View Customer</button>
        </form>
        
        <% if (customer != null) { %>
            <div class="table-container">
                <table>
                    <tr>
                        <th>Customer ID</th>
                        <td><%= customer.getCustomerId() %></td>
                    </tr>
                    <tr>
                        <th>Full Name</th>
                        <td><%= customer.getFullName() %></td>
                    </tr>
                    <tr>
                        <th>Address</th>
                        <td><%= customer.getAddress() %></td>
                    </tr>
                    <tr>
                        <th>Mobile Number</th>
                        <td><%= customer.getMobileNo() %></td>
                    </tr>
                    <tr>
                        <th>Email</th>
                        <td><%= customer.getEmailId() %></td>
                    </tr>
                    <tr>
                        <th>Account Type</th>
                        <td><%= customer.getAccountType() %></td>
                    </tr>
                    <tr>
                        <th>Balance</th>
                        <td><%= customer.getBalance() %></td>
                    </tr>
                    <tr>
                        <th>Date of Birth</th>
                        <td><%= new SimpleDateFormat("yyyy-MM-dd").format(customer.getDateOfBirth()) %></td>
                    </tr>
                    <tr>
                        <th>ID Proof</th>
                        <td><%= customer.getIdProof() %></td>
                    </tr>
                    <tr>
                        <th>Account Number</th>
                        <td><%= customer.getAccountNo() %></td>
                    </tr>
                </table>
            </div>
            <form action="EditCustomerServlet" method="get">
                <input type="hidden" name="accountNo" value="<%= customer.getAccountNo() %>">
                <button type="submit" class="submit-btn">Edit Customer</button>
            </form>
        <% } else if (request.getParameter("accountNo") != null) { %>
            <p>No customer found for account number: <%= request.getParameter("accountNo") %></p>
        <% } %>
        
        <br>
        <a href="adminDashboard.jsp" class="back-link">Back to Admin Dashboard</a>
    </div>
</body>
</html>
