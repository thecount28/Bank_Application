package com.banking.util;

import com.banking.model.Transaction;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPCell;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;
import java.text.SimpleDateFormat;


public class PDFUtil {

    public static void generatePDF(List<Transaction> transactions, String filePath) {
        Document pdfDocument = new Document();

        try {
            FileOutputStream fileOutputStream = new FileOutputStream(filePath);
            PdfWriter writer = PdfWriter.getInstance(pdfDocument, fileOutputStream);
            pdfDocument.open();

            // Add title
            pdfDocument.add(new Paragraph("Transaction History\n\n"));

            // Create table with 5 columns
            PdfPTable table = new PdfPTable(5);
            table.setWidths(new float[]{2, 2, 2, 2, 2});

            // Add table headers
            table.addCell("Transaction ID");
            table.addCell("Customer ID");
            table.addCell("Amount");
            table.addCell("Type");
            table.addCell("Date");

            // Add transaction rows
            for (Transaction transaction : transactions) {
                table.addCell(String.valueOf(transaction.getTransactionId()));
                table.addCell(String.valueOf(transaction.getCustomerId()));
                table.addCell(String.format("%.2f", transaction.getAmount()));
                table.addCell(transaction.getTransactionType());
                table.addCell(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(transaction.getTransactionDate()));
            }

            // Add table to document
            pdfDocument.add(table);

            pdfDocument.close();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void createPdf(String inputFile, String outputFile, boolean isPictureFile) {
        Document pdfDocument = new Document(new Rectangle(2780, 2525));

        try {
            FileOutputStream fileOutputStream = new FileOutputStream(outputFile);
            PdfWriter writer = PdfWriter.getInstance(pdfDocument, fileOutputStream);
            pdfDocument.open();

            if (isPictureFile) {
                pdfDocument.add(com.itextpdf.text.Image.getInstance(inputFile));
            } else {
                // Read file content using java.nio.file.Files
                String content = new String(Files.readAllBytes(new File(inputFile).toPath()), StandardCharsets.UTF_8);
                pdfDocument.add(new Paragraph(content));
            }

            pdfDocument.close();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // Example usage
        PDFUtil pdfUtil = new PDFUtil();
        // Generate PDF with table
        // pdfUtil.generatePDF(transactionsList, "transactions.pdf");
    }
}
