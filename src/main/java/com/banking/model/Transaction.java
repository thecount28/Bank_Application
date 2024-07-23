package com.banking.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Transaction {
    private int transactionId;
    private int customerId;
    private double amount;
    private String transactionType;
    private Date transactionDate;

    // Getters and Setters
    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    // Custom method to get transaction details as a string
    public String getTransactionDetails() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return "Transaction ID: " + transactionId +
                ", Customer ID: " + customerId +
                ", Amount: " + amount +
                ", Type: " + transactionType +
                ", Date: " + dateFormat.format(transactionDate);
    }
}
