<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.banking.model.Customer" %>

<%
    Customer customer = (Customer) session.getAttribute("customer");
    if (customer == null) {
        response.sendRedirect("customerLogin.jsp?error=Please login first");
        return;
    }
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Customer Dashboard - SecureBank</title>
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@300;400;500&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
    <style>
        body {
            font-family: 'Roboto', sans-serif;
            background: linear-gradient(135deg, #f5f7fa 0%, #c3cfe2 100%);
            margin: 0;
            padding: 0;
            display: flex;
            height: 100vh;
            overflow: hidden;
        }

        .dashboard-container {
            display: flex;
            width: 100%;
        }

        .sidebar {
            width: 250px;
            background-color: #ffffff;
            border-radius: 10px;
            box-shadow: 0 14px 28px rgba(0,0,0,0.25), 0 10px 10px rgba(0,0,0,0.22);
            padding: 20px;
            position: fixed;
            height: 100vh;
            display: flex;
            flex-direction: column;
            justify-content: space-between;
        }

        .logo h2 {
            margin: 0;
            color: #0077be;
            text-align: center;
            margin-bottom: 20px;
        }

        .sidebar ul {
            list-style: none;
            padding: 0;
        }

        .sidebar ul li {
            margin-bottom: 20px;
        }

        .sidebar ul li a {
            text-decoration: none;
            color: #0077be;
            display: flex;
            align-items: center;
            font-weight: 500;
            transition: color 0.3s ease;
        }

        .sidebar ul li a.active, .sidebar ul li a:hover {
            color: #005fa3;
        }

        .sidebar ul li a i {
            margin-right: 10px;
            font-size: 1.2em;
        }

        .sidebar ul li:last-child a {
            color: #ff4b5c;
        }

        .sidebar ul li:last-child a:hover {
            color: #ff1e3b;
        }

        .customer-dashboard {
            margin-left: 270px;
            padding: 40px;
            width: calc(100% - 270px);
            overflow-y: auto;
            background-color: #ffffff;
            border-radius: 10px;
            box-shadow: 0 14px 28px rgba(0,0,0,0.25), 0 10px 10px rgba(0,0,0,0.22);
        }

        .customer-dashboard h2 {
            color: #0077be;
            margin-bottom: 20px;
        }

        .customer-dashboard p {
            margin: 10px 0;
            font-size: 1.1em;
        }
    </style>
</head>
<body>
    <div class="dashboard-container">
        <nav class="sidebar">
            <div class="logo">
                <h2>SecureBank</h2>
            </div>
            <ul>
                <li><a href="#" class="active"><i class="fas fa-home"></i> Dashboard</a></li>
                <li><a href="ViewTransactionsServlet"><i class="fas fa-list"></i> View Transactions</a></li>
                <li><a href="DepositServlet"><i class="fas fa-dollar-sign"></i> Deposit Money</a></li>
                <li><a href="WithdrawServlet"><i class="fas fa-money-check-alt"></i> Withdraw Money</a></li>
                <li><a href="CustomerSelfEditServlet"><i class="fas fa-edit"></i> Edit Details</a></li>
                <li><a href="closeAccount.jsp?accountNo=<%= customer.getAccountNo() %>"><i class="fas fa-trash-alt"></i> Close Account</a></li>
                <li><a href="customerChangePasswordServlet"><i class="fas fa-key"></i> Change Password</a></li>
                <li><a href="LogoutServlet"><i class="fas fa-sign-out-alt"></i> Logout</a></li>
            </ul>
        </nav>
        <main class="customer-dashboard">
            <h2>Welcome, <%= customer.getFullName() %>!</h2>
            <p>Account Number: <%= customer.getAccountNo() %></p>
            <p>Balance: <%= customer.getBalance() %></p>
        </main>
    </div>
</body>
</html>
