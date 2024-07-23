<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.banking.model.Admin" %>
<%
if (session == null || session.getAttribute("admin") == null) {
    response.sendRedirect("adminLogin.jsp?error=Please login first");
    return;
}
Admin admin = (Admin) session.getAttribute("admin");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Dashboard - SecureBank</title>
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@300;400;500&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
    <link rel="stylesheet" href="styles.css">
</head>
<body>
    <div class="dashboard-container">
        <nav class="sidebar">
            <div class="logo">
                <h2>SecureBank</h2>
            </div>
            <ul>
                <li><a href="#" class="active"><i class="fas fa-home"></i> Dashboard</a></li>
                <li><a href="RegisterCustomerServlet"><i class="fas fa-user-plus"></i> Register Customer</a></li>
                <li><a href="DeleteCustomerServlet"><i class="fas fa-user-minus"></i> Delete Customer</a></li>
                <li><a href="ViewCustomerServlet"><i class="fas fa-users"></i> View Customers</a></li>
                <li><a href="LogoutServlet"><i class="fas fa-sign-out-alt"></i> Logout</a></li>
            </ul>
        </nav>
        <main class="admin-dashboard">
            <h2>Welcome, <%= admin.getUsername() %>!</h2>
        </main>
    </div>
</body>
</html>