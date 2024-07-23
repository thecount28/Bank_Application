<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.banking.model.Transaction" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%
    List<Transaction> transactions = (List<Transaction>) request.getAttribute("transactions");
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>View Transactions - SecureBank</title>
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@300;400;500&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
    <link rel="stylesheet" href="styles.css"> <!-- Include your CSS file -->
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
        .container {
            background-color: #ffffff;
            border-radius: 10px;
            box-shadow: 0 14px 28px rgba(0,0,0,0.25), 0 10px 10px rgba(0,0,0,0.22);
            width: 80%;
            padding: 20px;
            max-width: 1200px;
        }
        h2 {
            text-align: center;
            margin-bottom: 20px;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-bottom: 20px;
        }
        table, th, td {
            border: 1px solid #ddd;
        }
        th, td {
            padding: 12px;
            text-align: left;
        }
        th {
            background-color: #0077be;
            color: white;
        }
        tr:nth-child(even) {
            background-color: #f2f2f2;
        }
        .links {
            display: flex;
            justify-content: space-between;
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
</head>
<body>
    <div class="container">
        <h2>Last 10 Transactions</h2>
        <table>
            <thead>
                <tr>
                    <th>Transaction ID</th>
                    <th>Amount</th>
                    <th>Type</th>
                    <th>Date</th>
                </tr>
            </thead>
            <tbody>
                <% 
                    for (Transaction transaction : transactions) {
                %>
                <tr>
                    <td><%= transaction.getTransactionId() %></td>
                    <td><%= transaction.getAmount() %></td>
                    <td><%= transaction.getTransactionType() %></td>
                    <td><%= dateFormat.format(transaction.getTransactionDate()) %></td>
                </tr>
                <% } %>
            </tbody>
        </table>
        <div class="links">
            <a href="CustomerDashboardServlet"><i class="fas fa-arrow-left"></i> Back to Dashboard</a>
            <a href="PrintTransactionsServlet"><i class="fas fa-print"></i> Print Transactions</a>
        </div>
    </div>
</body>
</html>
