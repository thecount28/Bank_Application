package com.banking.servlet;

import com.banking.dao.TransactionDAO;
import com.banking.model.Customer;
import com.banking.model.Transaction;
import com.banking.util.PDFUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@WebServlet("/PrintTransactionsServlet")
public class PrintTransactionsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("customer") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        Customer customer = (Customer) session.getAttribute("customer");
        int customerId = customer.getCustomerId();

        TransactionDAO transactionDAO = new TransactionDAO();
        List<Transaction> transactions = transactionDAO.getTransactionsByCustomerId(customerId);

        // Generate PDF file in a temporary directory
        String fileName = "transactions.pdf";
        String tempDir = System.getProperty("java.io.tmpdir");
        String filePath = tempDir + fileName;

        try {
            // Generate PDF
            PDFUtil.generatePDF(transactions, filePath);

            // Set response headers
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "attachment; filename=" + fileName);

            // Write PDF content to response output stream
            try (FileInputStream fis = new FileInputStream(filePath);
                 OutputStream os = response.getOutputStream()) {
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = fis.read(buffer)) != -1) {
                    os.write(buffer, 0, bytesRead);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            // Delete the temporary file after download
            Files.deleteIfExists(Paths.get(filePath));
        }
    }
}
