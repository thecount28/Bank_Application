package com.banking.servlet;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.banking.dao.TransactionDAO;
import com.banking.model.Customer;
import com.banking.model.Transaction;

@WebServlet("/ViewTransactionsServlet")
public class ViewTransactionsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private TransactionDAO transactionDAO;

    public ViewTransactionsServlet() {
        super();
        transactionDAO = new TransactionDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Customer customer = (Customer) request.getSession().getAttribute("customer");
        if (customer != null) {
            List<Transaction> transactions = transactionDAO.getTransactionsByCustomerId(customer.getCustomerId());
            request.setAttribute("transactions", transactions);
            request.getRequestDispatcher("viewTransactions.jsp").forward(request, response);
        } else {
            response.sendRedirect("login.jsp?error=Please login first");
        }
    }
}
