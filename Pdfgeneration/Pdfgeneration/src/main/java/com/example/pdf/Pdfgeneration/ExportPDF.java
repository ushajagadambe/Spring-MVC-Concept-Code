package com.example.pdf.Pdfgeneration;

import com.itextpdf.layout.font.FontProvider;
import com.itextpdf.layout.font.FontSet;
import com.itextpdf.text.*;
import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.*;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.*;
import java.util.*;

import com.itextpdf.text.*;
import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.stream.IntStream;

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
           table.addCell(hospital.getHindi_Name());
        }
    }
    public  void exportpdf(HttpServletResponse response) throws IOException, DocumentException {
        Document document=new Document(PageSize.A4);
        PdfWriter.getInstance(document,response.getOutputStream());
        document.open();
        FontFactory.register("Font/Mangal Regular.ttf");
        Font hindiFont = FontFactory.getFont("Mangal", BaseFont.IDENTITY_H, true);
        Paragraph p=new Paragraph("\n" +
                "हिंदी समर्थन",hindiFont);
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