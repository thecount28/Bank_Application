package com.banking.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.banking.dao.CustomerDAO;
import com.banking.model.Customer;

@WebServlet("/CustomerSelfEditServlet")
public class CustomerSelfEditServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private CustomerDAO customerDAO;

    public CustomerSelfEditServlet() {
        super();
        customerDAO = new CustomerDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("customer");

        if (customer == null) {
            response.sendRedirect("login.jsp?error=Please login first");
        } else {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String formattedDob = sdf.format(customer.getDateOfBirth());
            request.setAttribute("customer", customer);
            request.setAttribute("formattedDob", formattedDob);
            request.getRequestDispatcher("editCustomerSelfDetails.jsp").forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("customer");

        if (customer == null) {
            response.sendRedirect("login.jsp?error=Please login first");
            return;
        }

        String fullName = request.getParameter("fullName");
        String emailId = request.getParameter("emailId");
        String mobileNo = request.getParameter("mobileNo");
        String address = request.getParameter("address");

        customer.setFullName(fullName);
        customer.setEmailId(emailId);
        customer.setMobileNo(mobileNo);
        customer.setAddress(address);

        boolean isUpdated = customerDAO.updateCustomerDetails(customer);

        if (isUpdated) {
            session.setAttribute("customer", customer);
            response.sendRedirect("customerDashboard.jsp?success=Details updated successfully");
        } else {
            response.sendRedirect("editCustomerSelfDetails.jsp?error=Update failed");
        }
    }
}
