package com.banking.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.banking.dao.CustomerDAO;
import com.banking.model.Customer;

@WebServlet("/UpdateCustomerServlet")
public class UpdateCustomerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private CustomerDAO customerDAO;

    public UpdateCustomerServlet() {
        super();
        customerDAO = new CustomerDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String customerIdStr = request.getParameter("customerId");
        if (customerIdStr == null || customerIdStr.isEmpty()) {
            response.sendRedirect("editCustomer.jsp?error=Invalid%20Customer%20ID");
            return;
        }

        int customerId;
        try {
            customerId = Integer.parseInt(customerIdStr);
        } catch (NumberFormatException e) {
            response.sendRedirect("editCustomer.jsp?error=Invalid%20Customer%20ID%20Format");
            return;
        }

        String fullName = request.getParameter("fullName");
        String address = request.getParameter("address");
        String mobileNo = request.getParameter("mobileNo");
        String emailId = request.getParameter("emailId");
        String accountType = request.getParameter("accountType");
        double balance;
        try {
            balance = Double.parseDouble(request.getParameter("balance"));
        } catch (NumberFormatException e) {
            response.sendRedirect("editCustomer.jsp?error=Invalid%20Balance%20Format");
            return;
        }
        String dateOfBirth = request.getParameter("dateOfBirth");
        String idProof = request.getParameter("idProof");
        String accountNo = request.getParameter("accountNo");
        String password = request.getParameter("password");

        Customer customer = new Customer();
        customer.setCustomerId(customerId);
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

        boolean isUpdated = customerDAO.updateCustomer(customer);

        if (isUpdated) {
            response.sendRedirect("viewCustomer.jsp?success=Customer%20Details%20Updated");
        } else {
            response.sendRedirect("editCustomer.jsp?error=Error%20Updating%20Customer%20Details");
        }
    }
}
