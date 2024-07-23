package com.banking.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.banking.model.Admin;
import com.banking.util.DBConnectionUtil;

public class AdminDAO {
    private Connection connection;

    public AdminDAO() {
        connection = DBConnectionUtil.getConnection();
    }

    public boolean login(Admin admin) {
        String sql = "SELECT * FROM admin WHERE username = ? AND password = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, admin.getUsername());
            statement.setString(2, admin.getPassword());
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next(); // Returns true if credentials match, false otherwise
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
