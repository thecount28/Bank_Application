package com.banking.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.banking.dao.CustomerDAO;
import com.banking.dao.TransactionDAO;
import com.banking.model.Customer;
import com.banking.model.Transaction;

@WebServlet("/DepositServlet")
public class DepositServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private CustomerDAO customerDAO;
    private TransactionDAO transactionDAO;

    public DepositServlet() {
        super();
        customerDAO = new CustomerDAO();
        transactionDAO = new TransactionDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("customer");

        if (customer == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        String amountParam = request.getParameter("amount");

        if (amountParam == null || amountParam.trim().isEmpty()) {
            response.sendRedirect("deposit.jsp?error=Invalid Amount");
            return;
        }

        double amount;
        try {
            amount = Double.parseDouble(amountParam);
        } catch (NumberFormatException e) {
            response.sendRedirect("deposit.jsp?error=Invalid Amount Format");
            return;
        }

        // Update balance for the logged-in customer
        double newBalance = customer.getBalance() + amount;
        boolean isUpdated = customerDAO.updateCustomerBalance(customer.getAccountNo(), newBalance);

        if (isUpdated) {
            // Update customer object in session with new balance
            customer.setBalance(newBalance);
            session.setAttribute("customer", customer);

            Transaction transaction = new Transaction();
            transaction.setCustomerId(customer.getCustomerId());
            transaction.setAmount(amount);
            transaction.setTransactionType("Deposit");
            transactionDAO.recordTransaction(transaction);

            response.sendRedirect("customerDashboard.jsp?success=Deposit Successful");
        } else {
            response.sendRedirect("deposit.jsp?error=Deposit Failed");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
