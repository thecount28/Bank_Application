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
import java.util.List;

@WebServlet("/CustomerDashboardServlet")
public class CustomerDashboardServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public CustomerDashboardServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("customer");
        if (customer != null) {
            // Refresh the customer data
            CustomerDAO customerDAO = new CustomerDAO();
            customer = customerDAO.getCustomerByAccountNo(customer.getAccountNo());
            session.setAttribute("customer", customer);

            // Get the last 10 transactions
            TransactionDAO transactionDAO = new TransactionDAO();
            List<Transaction> transactions = transactionDAO.getTransactionsByCustomerId(customer.getCustomerId());
            request.setAttribute("transactions", transactions);

            request.getRequestDispatcher("customerDashboard.jsp").forward(request, response);
        } else {
            response.sendRedirect("login.jsp?error=Please login first");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
