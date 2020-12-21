package com.example.pdf.Pdfgeneration;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.security.GeneralSecurityException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.Events;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

@RestController
public class HospitalController {

    @Autowired
    HospitalRepository hospitalRepository;

    @GetMapping("/pdf/view/sentence")
    public void hosptalpdf(HttpServletResponse response) throws IOException, org.dom4j.DocumentException, com.itextpdf.text.DocumentException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=view_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);
        List<Hospital> hospitalList = new ArrayList<Hospital>();
        hospitalList = hospitalRepository.findAll();

        ExportPDF exportPDF = new ExportPDF(hospitalList);
        exportPDF.exportpdf(response);

    }

    @GetMapping("/")
    public String ViewHomepage(Model model, HttpServletRequest httpServletRequest) throws ParseException {
        model.addAttribute("page titel", "Home-shome Admimn");
        Date d1=new Date(2020,4,07);

        Date d2=new Date(2020,10,07);

        long diif= ChronoUnit.DAYS.between(d1.toInstant(), d2.toInstant());
        System.out.println(diif);
        return "Welcome";

    }

}