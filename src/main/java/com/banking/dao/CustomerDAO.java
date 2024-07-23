package com.banking.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.banking.model.Customer;
import com.banking.util.DBConnectionUtil;

public class CustomerDAO {
    private Connection connection;

    public CustomerDAO() {
        connection = DBConnectionUtil.getConnection();
    }

    public boolean registerCustomer(Customer customer, String accountNo, String password) {
        String sqlCustomer = "INSERT INTO customer (full_name, address, mobile_no, email_id, account_type, balance, date_of_birth, id_proof, account_no, password) "
        		+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statementCustomer = connection.prepareStatement(sqlCustomer, PreparedStatement.RETURN_GENERATED_KEYS)) {
            statementCustomer.setString(1, customer.getFullName());
            statementCustomer.setString(2, customer.getAddress());
            statementCustomer.setString(3, customer.getMobileNo());
            statementCustomer.setString(4, customer.getEmailId());
            statementCustomer.setString(5, customer.getAccountType());
            statementCustomer.setDouble(6, customer.getBalance());
            statementCustomer.setDate(7, new java.sql.Date(customer.getDateOfBirth().getTime()));
            statementCustomer.setString(8, customer.getIdProof());
            statementCustomer.setString(9, accountNo);
            statementCustomer.setString(10, password);

            int rowsAffected = statementCustomer.executeUpdate();
            if (rowsAffected > 0) {
                ResultSet generatedKeys = statementCustomer.getGeneratedKeys();
                if (generatedKeys.next()) {
                    customer.setCustomerId(generatedKeys.getInt(1));
                }
                return true;
            }
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Customer getCustomerByAccountNo(String accountNo) {
        Customer customer = null;
        String sql = "SELECT * FROM customer WHERE account_no = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, accountNo);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                customer = new Customer();
                customer.setCustomerId(resultSet.getInt("customer_id"));
                customer.setFullName(resultSet.getString("full_name"));
                customer.setAddress(resultSet.getString("address"));
                customer.setMobileNo(resultSet.getString("mobile_no"));
                customer.setEmailId(resultSet.getString("email_id"));
                customer.setAccountType(resultSet.getString("account_type"));
                customer.setBalance(resultSet.getDouble("balance"));
                customer.setDateOfBirth(resultSet.getDate("date_of_birth"));
                customer.setIdProof(resultSet.getString("id_proof"));
                customer.setAccountNo(resultSet.getString("account_no"));
                customer.setPassword(resultSet.getString("password"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customer;
    }

    public boolean updateCustomer(Customer customer) {
        String sql = "UPDATE customer SET full_name = ?, address = ?, mobile_no = ?, email_id = ?, account_type = ?, balance = ?, date_of_birth = ?, id_proof = ?, password = ? WHERE account_no = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, customer.getFullName());
            statement.setString(2, customer.getAddress());
            statement.setString(3, customer.getMobileNo());
            statement.setString(4, customer.getEmailId());
            statement.setString(5, customer.getAccountType());
            statement.setDouble(6, customer.getBalance());
            statement.setDate(7, new java.sql.Date(customer.getDateOfBirth().getTime()));
            statement.setString(8, customer.getIdProof());
            statement.setString(9, customer.getPassword());
            statement.setString(10, customer.getAccountNo());

            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean login(String username, String password) {
        String sql = "SELECT * FROM customer WHERE account_no = ? AND password = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Customer getCustomerByUsername(String username) {
        String sql = "SELECT * FROM customer WHERE account_no = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Customer customer = new Customer();
                customer.setCustomerId(resultSet.getInt("customer_id"));
                customer.setFullName(resultSet.getString("full_name"));
                customer.setAddress(resultSet.getString("address"));
                customer.setMobileNo(resultSet.getString("mobile_no"));
                customer.setEmailId(resultSet.getString("email_id"));
                customer.setAccountType(resultSet.getString("account_type"));
                customer.setBalance(resultSet.getDouble("balance"));
                customer.setDateOfBirth(resultSet.getDate("date_of_birth"));
                customer.setIdProof(resultSet.getString("id_proof"));
                customer.setAccountNo(resultSet.getString("account_no"));
                customer.setPassword(resultSet.getString("password"));
                return customer;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean deleteCustomer(String accountNo) {
        String sql = "DELETE FROM customer WHERE account_no = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, accountNo);
            int rowsDeleted = statement.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean updateCustomerBalance(String accountNo, double newBalance) {
        String sql = "UPDATE customer SET balance = ? WHERE account_no = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setDouble(1, newBalance);
            statement.setString(2, accountNo);
            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean closeCustomerAccount(String accountNo) {
        String sql = "UPDATE customer SET status = 'closed' WHERE account_no = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, accountNo);
            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public double getBalance(String accountNo) {
        double balance = 0.0;
        String sql = "SELECT balance FROM customer WHERE account_no = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, accountNo);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                balance = resultSet.getDouble("balance");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return balance;
    }
    
    public boolean withdraw(String accountNo, double amount) {
        double currentBalance = getBalance(accountNo);
        if (currentBalance >= amount) {
            String sql = "UPDATE customer SET balance = ? WHERE account_no = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setDouble(1, currentBalance - amount);
                statement.setString(2, accountNo);
                int rowsUpdated = statement.executeUpdate();
                return rowsUpdated > 0;
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        } else {
            return false; // Insufficient balance
        }
    }
    
    public boolean deposit(String accountNo, double amount) {
        String sql = "UPDATE customer SET balance = balance + ? WHERE account_no = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setDouble(1, amount);
            statement.setString(2, accountNo);
            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean updateCustomerDetails(Customer customer) {
        String sql = "UPDATE customer SET full_name = ?, address = ?, mobile_no = ?, email_id = ?, account_type = ?, balance = ?, date_of_birth = ?, id_proof = ? WHERE account_no = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, customer.getFullName());
            statement.setString(2, customer.getAddress());
            statement.setString(3, customer.getMobileNo());
            statement.setString(4, customer.getEmailId());
            statement.setString(5, customer.getAccountType());
            statement.setDouble(6, customer.getBalance());
            statement.setDate(7, new java.sql.Date(customer.getDateOfBirth().getTime()));
            statement.setString(8, customer.getIdProof());
            statement.setString(9, customer.getAccountNo());

            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean updateCustomerPassword(String accountNo, String newPassword) {
        String sql = "UPDATE customer SET password = ? WHERE account_no = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, newPassword);
            statement.setString(2, accountNo);

            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }



}

