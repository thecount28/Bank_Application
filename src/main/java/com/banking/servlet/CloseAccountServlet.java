package com.banking.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/closeAccountServlet")
public class CloseAccountServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accountNo = request.getParameter("accountNo");

        if (accountNo != null && !accountNo.isEmpty()) {
            try {
                // Establish database connection
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/banking_application7", "root", "password");

                // Check balance to ensure it's zero
                double balance = 0.00;
                boolean accountExists = false;

                try (PreparedStatement pstmtSelect = conn.prepareStatement("SELECT balance FROM customer WHERE account_no = ?")) {
                    pstmtSelect.setString(1, accountNo);
                    ResultSet rs = pstmtSelect.executeQuery();

                    if (rs.next()) {
                        balance = rs.getDouble("balance");
                        accountExists = true;
                    }
                }

                if (accountExists && balance == 0.00) {
                    // Close account
                    try (PreparedStatement pstmtDelete = conn.prepareStatement("DELETE FROM customer WHERE account_no = ?")) {
                        pstmtDelete.setString(1, accountNo);
                        pstmtDelete.executeUpdate();
                    }

                    response.sendRedirect("login.jsp"); // Redirect to homepage or success page
                } else {
                    // Account doesn't exist or balance isn't zero
                    response.getWriter().println("Account cannot be closed. Please check the account details and ensure the balance is zero.");
                }

                conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
                response.getWriter().println("Error: Unable to process request.");
            }
        } else {
            response.getWriter().println("Invalid account number.");
        }
    }
}