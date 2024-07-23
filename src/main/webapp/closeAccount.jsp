<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Close Account</title>
    <link rel="stylesheet" href="styles.css">
    <!-- Add any necessary CSS or JavaScript links -->
</head>
<body>
    <div class="container">
        <div class="card">
            <h2>Close Account Confirmation</h2>

            <%@ page import="java.sql.*" %>
            <%@ page import="javax.servlet.*" %>
            <%@ page import="javax.servlet.http.*" %>

            <%
                String accountNo = request.getParameter("accountNo");

                // Check if accountNo is not null and not empty
                if (accountNo != null && !accountNo.isEmpty()) {
                    double balance = 0.00;
                    boolean accountExists = false;

                    try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/banking_application7", "root", "password");
                         PreparedStatement pstmtSelect = conn.prepareStatement("SELECT balance FROM customer WHERE account_no = ?")) {

                        pstmtSelect.setString(1, accountNo);
                        ResultSet rs = pstmtSelect.executeQuery();

                        // Check if accountNo exists in the database
                        if (rs.next()) {
                            balance = rs.getDouble("balance");
                            accountExists = true;
                        }

                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }

                    // Display account information if account exists
                    if (accountExists) {
            %>

            <p>Account Number: <%= accountNo %></p>
            <p>Current Balance: $<%= balance %></p>

            <% if (balance > 0.00) { %>
                <p class="warning">Warning: There is still a balance of $<%= balance %> in this account. Are you sure you want to close it?</p>
            <% } %>

            <form action="closeAccountServlet" method="POST">
                <input type="hidden" name="accountNo" value="<%= accountNo %>">
                <button type="submit" class="btn btn-primary">Confirm Close Account</button>
            </form>

            <br>
            <a href="customerLogin.jsp" class="btn btn-secondary">Back to Home</a>

            <% } else { %>
                <p class="error">Invalid Account Number.</p>
                <a href="customerLogin.jsp" class="btn btn-secondary">Back to Home</a>
            <% } %>

            <% } else { %>
                <p class="error">Account Number is required.</p>
                <a href="customerLogin.jsp" class="btn btn-secondary">Back to Home</a>
            <% } %>
        </div>
    </div>
</body>
</html>
