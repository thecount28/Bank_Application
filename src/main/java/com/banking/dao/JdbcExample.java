package com.banking.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcExample {
   public static void main(String[] args) {
        Connection con = null;
        try {
            // Step 1. Registering the Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Step 2. Establish the connection
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/banking_application6", "root", "Venkat@123");
            System.out.println("Connection created");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}