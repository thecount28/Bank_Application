package com.banking.servlet;

import java.io.IOException;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.banking.dao.CustomerDAO;
import com.banking.model.Customer;

@WebServlet("/RegisterCustomerServlet")
public class RegisterCustomerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String fullName = request.getParameter("fullName");
        String address = request.getParameter("address");
        String mobileNo = request.getParameter("mobileNo");
        String emailId = request.getParameter("emailId");
        String accountType = request.getParameter("accountType");
        String dateOfBirth = request.getParameter("dateOfBirth");
        String idProof = request.getParameter("idProof");

        // Validate and parse balance
        double balance = 0.0; // Default balance value
        String balanceParam = request.getParameter("balance");
        if (balanceParam != null && !balanceParam.trim().isEmpty()) {
            try {
                balance = Double.parseDouble(balanceParam);
            } catch (NumberFormatException e) {
                // Handle parsing exception
                e.printStackTrace();
                request.setAttribute("errorMessage", "Invalid balance format");
                request.getRequestDispatcher("registerCustomer.jsp").forward(request, response);
                return;
            }
        } else {
            // Handle case where balance parameter is missing or empty
            request.setAttribute("errorMessage", "Balance cannot be empty");
            request.getRequestDispatcher("registerCustomer.jsp").forward(request, response);
            return;
        }

        // Validate other required fields
        if (fullName == null || fullName.isEmpty() || address == null || address.isEmpty() ||
            mobileNo == null || mobileNo.isEmpty() || emailId == null || emailId.isEmpty() ||
            accountType == null || accountType.isEmpty() || dateOfBirth == null || dateOfBirth.isEmpty() ||
            idProof == null || idProof.isEmpty()) {
            request.setAttribute("errorMessage", "One or more required fields are missing");
            request.getRequestDispatcher("registerCustomer.jsp").forward(request, response);
            return;
        }

        // Generate account number and temporary password
        String accountNo = generateAccountNo();
        String password = generateTempPassword();

        // Create Customer object
        Customer customer = new Customer();
        customer.setFullName(fullName);
        customer.setAddress(address);
        customer.setMobileNo(mobileNo);
        customer.setEmailId(emailId);
        customer.setAccountType(accountType);
        customer.setBalance(balance);
        customer.setDateOfBirth(java.sql.Date.valueOf(dateOfBirth));
        customer.setIdProof(idProof);
        customer.setAccountNo(accountNo);
        customer.setPassword(password);

        // Save customer to database using DAO
        CustomerDAO customerDAO = new CustomerDAO();
        if (customerDAO.registerCustomer(customer, accountNo, password)) {
            // Registration successful

            response.sendRedirect("adminDashboard.jsp");
        } else {
            // Registration failed
            request.setAttribute("errorMessage", "Customer registration failed");
            request.getRequestDispatcher("registerCustomer.jsp").forward(request, response);
        }
    }

    private String generateAccountNo() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 12; i++) {
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }

    private String generateTempPassword() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            sb.append((char) (random.nextInt(26) + 'a'));
        }
        return sb.toString();
    }
}
