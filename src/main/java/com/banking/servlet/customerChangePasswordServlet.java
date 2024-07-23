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

@WebServlet("/customerChangePasswordServlet")
public class customerChangePasswordServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private CustomerDAO customerDAO;

    public customerChangePasswordServlet() {
        super();
        customerDAO = new CustomerDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("customer");

        if (customer == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        request.getRequestDispatcher("customerChangePassword.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("customer");

        if (customer == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        String currentPassword = request.getParameter("currentPassword");
        String newPassword = request.getParameter("newPassword");
        String confirmPassword = request.getParameter("confirmPassword");

        if (!customer.getPassword().equals(currentPassword)) {
            request.setAttribute("error", "Current password is incorrect.");
            request.getRequestDispatcher("customerChangePassword.jsp").forward(request, response);
            return;
        }

        if (!newPassword.equals(confirmPassword)) {
            request.setAttribute("error", "New password and confirm password do not match.");
            request.getRequestDispatcher("customerChangePassword.jsp").forward(request, response);
            return;
        }

        customer.setPassword(newPassword);

        boolean isUpdated = customerDAO.updateCustomerPassword(customer.getAccountNo(), newPassword);

        if (isUpdated) {
            session.setAttribute("customer", customer);
            request.setAttribute("success", "Password updated successfully.");
        } else {
            request.setAttribute("error", "Failed to update password.");
        }
        request.getRequestDispatcher("customerChangePassword.jsp").forward(request, response);
    }
}
