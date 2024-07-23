package com.banking.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.banking.dao.CustomerDAO;
import com.banking.model.Customer;

@WebServlet("/EditCustomerServlet")
public class EditCustomerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private CustomerDAO customerDAO;

    public EditCustomerServlet() {
        super();
        customerDAO = new CustomerDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accountNo = request.getParameter("accountNo");

        if (accountNo != null && !accountNo.isEmpty()) {
            Customer customer = customerDAO.getCustomerByAccountNo(accountNo);

            if (customer != null) {
                request.setAttribute("customer", customer);
                request.getRequestDispatcher("editCustomer.jsp").forward(request, response);
            } else {
                response.sendRedirect("viewCustomer.jsp?error=Customer%20Not%20Found");
            }
        } else {
            response.sendRedirect("viewCustomer.jsp?error=Invalid%20Account%20Number");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
