<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit Customer Details</title>
    <link rel="stylesheet" href="styles.css"> <!-- Optional: Include your CSS file -->
</head>
<body>
    <h2>Edit Customer Details</h2>
    
    <% 
    com.banking.model.Customer customer = (com.banking.model.Customer) request.getAttribute("customer");
    
    if (customer != null) {
    %>
    <form action="UpdateCustomerServlet" method="post">
        <input type="hidden" name="customerId" value="<%= customer.getCustomerId() %>">
        
        <label for="fullName">Full Name:</label>
        <input type="text" id="fullName" name="fullName" value="<%= customer.getFullName() %>" required><br>
        
        <label for="address">Address:</label>
        <input type="text" id="address" name="address" value="<%= customer.getAddress() %>" required><br>
        
        <label for="mobileNo">Mobile Number:</label>
        <input type="text" id="mobileNo" name="mobileNo" value="<%= customer.getMobileNo() %>" required><br>
        
        <label for="emailId">Email:</label>
        <input type="email" id="emailId" name="emailId" value="<%= customer.getEmailId() %>" required><br>
        
        <label for="accountType">Account Type:</label>
        <input type="text" id="accountType" name="accountType" value="<%= customer.getAccountType() %>" required><br>
        
        <label for="balance">Balance:</label>
        <input type="number" id="balance" name="balance" value="<%= customer.getBalance() %>" required><br>
        
        <label for="dateOfBirth">Date of Birth:</label>
        <input type="date" id="dateOfBirth" name="dateOfBirth" value="<%= new SimpleDateFormat("yyyy-MM-dd").format(customer.getDateOfBirth()) %>" required><br>
        
        <label for="idProof">ID Proof:</label>
        <input type="text" id="idProof" name="idProof" value="<%= customer.getIdProof() %>" required><br>
        
        <label for="accountNo">Account Number:</label>
        <input type="text" id="accountNo" name="accountNo" value="<%= customer.getAccountNo() %>" readonly><br>
        
        <input type="hidden" name="password" value="<%= customer.getPassword() %>">
        
        <button type="submit">Update Customer</button>
    </form>
    
    <% 
    } else {
    %>
    <p>No customer details found to edit.</p>
    <% 
    }
    %>
    
    <br>
    <a href="viewCustomer.jsp">Back to View Customer</a>
</body>
</html>
