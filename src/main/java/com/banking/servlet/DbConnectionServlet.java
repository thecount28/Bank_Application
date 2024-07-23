package com.banking.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/dbconnect")
public class DbConnectionServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Database credentials
    private static final String DB_URL = "jdbc:mysql://localhost:3306/banking_application7";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "password";

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            // Load the MySQL driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            out.println("<p>MySQL JDBC Driver Registered!</p>");

            // Get a connection to the database
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            if (connection != null) {
                out.println("<h1>Database connection successful!</h1>");

                // Create a statement object to execute the query
                statement = connection.createStatement();
                String sql = "SELECT * FROM accounts"; // Replace 'accounts' with your table name
                resultSet = statement.executeQuery(sql);

                // Start the HTML table
                out.println("<table border='1'><tr><th>Account ID</th><th>Account Holder Name</th><th>Balance</th></tr>");

                // Iterate through the result set and display each row
                while (resultSet.next()) {
                    int accountId = resultSet.getInt("account_id"); // Replace with your column name
                    String accountHolderName = resultSet.getString("account_holder_name"); // Replace with your column name
                    double balance = resultSet.getDouble("balance"); // Replace with your column name

                    out.println("<tr>");
                    out.println("<td>" + accountId + "</td>");
                    out.println("<td>" + accountHolderName + "</td>");
                    out.println("<td>" + balance + "</td>");
                    out.println("</tr>");
                }

                // End the HTML table
                out.println("</table>");
            } else {
                out.println("<h1>Failed to connect to the database.</h1>");
            }
        } catch (ClassNotFoundException e) {
            out.println("<h1>ClassNotFoundException: " + e.getMessage() + "</h1>");
        } catch (SQLException e) {
            out.println("<h1>SQLException: " + e.getMessage() + "</h1>");
        } finally {
            // Close the result set, statement, and connection
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                    out.println("<p>Database connection closed.</p>");
                }
            } catch (SQLException e) {
                out.println("<h1>SQLException on closing: " + e.getMessage() + "</h1>");
            }
        }
    }
}