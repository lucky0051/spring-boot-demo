package com.ks.practice.util;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Map;

public class PdfUtil {
    public class PdfUtils {
        public static ByteArrayOutputStream generatePdfStream(List<Map<String, Object>> queryResults) throws DocumentException {
            Document document = new Document();
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            PdfWriter.getInstance(document, outputStream);
            document.open();

            document.add(new Paragraph("\n"));
            // Write data rows
            for (Map<String, Object> row : queryResults) {
                Font boldFont = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD);
                for (Map.Entry<String, Object> entry : row.entrySet()) {
                    String k = entry.getKey();
                    Object v = entry.getValue();
                    Paragraph paragraph = new Paragraph(k + " :" + v.toString(), boldFont);
                    document.add(paragraph);
                }
                document.add(new Paragraph("\n"));
            }
            document.close();
            return outputStream;
        }
    }
}
