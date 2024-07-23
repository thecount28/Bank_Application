package com.banking.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.banking.dao.CustomerDAO;
import com.banking.model.Customer;

@WebServlet("/CustomerLoginServlet")
public class CustomerLoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public CustomerLoginServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accountNo = request.getParameter("username");
        String password = request.getParameter("password");

        CustomerDAO customerDAO = new CustomerDAO();
        boolean isValidUser = customerDAO.login(accountNo, password);

        if (isValidUser) {
            HttpSession session = request.getSession();
            Customer customer = customerDAO.getCustomerByAccountNo(accountNo);
            session.setAttribute("customer", customer);
            response.sendRedirect("CustomerDashboardServlet");
        } else {
            response.sendRedirect("login.jsp?error=Invalid username or password");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("login.jsp");
    }
}
