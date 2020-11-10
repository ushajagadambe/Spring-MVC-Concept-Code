package com.example.pdf.Pdfgeneration;

import com.itextpdf.text.*;
import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class ExportPDF {
    private  List<Hospital> list;

    public ExportPDF(List<Hospital> list) {
        this.list = list;
    }

    private  void writeTableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setPadding(5);

        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(BaseColor.BLACK);

        cell.setPhrase(new Phrase("ID", font));

        table.addCell(cell);

        cell.setPhrase(new Phrase("Name", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("location", font));
        table.addCell(cell);

    }

    private  void writeTableData(PdfPTable table) {
        for (Hospital hospital : list) {
            table.addCell(String.valueOf(hospital.getId()));
            table.addCell(hospital.getHospitalName());
            table.addCell(hospital.getLocation());

        }
    }
    public  void exportpdf(HttpServletResponse response) throws IOException, DocumentException {
        Document document=new Document(PageSize.A4);
        PdfWriter.getInstance(document,response.getOutputStream());
        document.open();
        Font font= FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(18);
        Paragraph p=new Paragraph("list",font);
        p.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(p);
        PdfPTable table = new PdfPTable(3);
        table.setWidthPercentage(100f);
        table.setWidths(new float[] {1.5f, 3.5f, 3.0f});
        table.setSpacingBefore(10);

        writeTableHeader(table);
        writeTableData(table);

        document.add(table);



        document.close();


    }
}
