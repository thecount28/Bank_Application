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

@WebServlet("/WithdrawServlet")
public class WithdrawServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private CustomerDAO customerDAO;
    private TransactionDAO transactionDAO;

    public WithdrawServlet() {
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
            response.sendRedirect("withdraw.jsp?error=Invalid Amount");
            return;
        }

        double amount;
        try {
            amount = Double.parseDouble(amountParam);
        } catch (NumberFormatException e) {
            response.sendRedirect("withdraw.jsp?error=Invalid Amount Format");
            return;
        }

        if (customer.getBalance() >= amount) {
            double newBalance = customer.getBalance() - amount;
            boolean isUpdated = customerDAO.updateCustomerBalance(customer.getAccountNo(), newBalance);

            if (isUpdated) {
                // Update customer object in session with new balance
                customer.setBalance(newBalance);
                session.setAttribute("customer", customer);

                Transaction transaction = new Transaction();
                transaction.setCustomerId(customer.getCustomerId());
                transaction.setAmount(amount);
                transaction.setTransactionType("Withdraw");
                transactionDAO.recordTransaction(transaction);

                response.sendRedirect("customerDashboard.jsp?success=Withdraw Successful");
            } else {
                response.sendRedirect("withdraw.jsp?error=Withdraw Failed");
            }
        } else {
            response.sendRedirect("withdraw.jsp?error=Insufficient Balance");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
