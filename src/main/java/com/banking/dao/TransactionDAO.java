package com.banking.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.banking.model.Transaction;
import com.banking.util.DBConnectionUtil;

public class TransactionDAO {
    private Connection connection;

    public TransactionDAO() {
        connection = DBConnectionUtil.getConnection();
    }

    public boolean recordTransaction(Transaction transaction) {
        String sql = "INSERT INTO transaction (customer_id, amount, transaction_type) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, transaction.getCustomerId());
            statement.setDouble(2, transaction.getAmount());
            statement.setString(3, transaction.getTransactionType());
            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Transaction> getTransactionsByCustomerId(int customerId) {
        List<Transaction> transactions = new ArrayList<>();
        String sql = "SELECT * FROM transaction WHERE customer_id = ? ORDER BY transaction_date DESC LIMIT 10";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, customerId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Transaction transaction = new Transaction();
                transaction.setTransactionId(resultSet.getInt("transaction_id"));
                transaction.setCustomerId(resultSet.getInt("customer_id"));
                transaction.setAmount(resultSet.getDouble("amount"));
                transaction.setTransactionType(resultSet.getString("transaction_type"));
                transaction.setTransactionDate(resultSet.getTimestamp("transaction_date"));
                transactions.add(transaction);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transactions;
    }
}
