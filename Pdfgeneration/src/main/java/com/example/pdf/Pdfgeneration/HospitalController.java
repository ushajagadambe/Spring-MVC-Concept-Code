package com.example.pdf.Pdfgeneration;

import com.itextpdf.text.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@RestController

public class HospitalController {
    @Autowired
    HospitalService hospitalService;

    @GetMapping("/pdf/view/")
    public void hosptalpdf(HttpServletResponse response) throws IOException, DocumentException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=view_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);
       List<Hospital> list= hospitalService.getAllList();
        ExportPDF exportPDF= new ExportPDF(list);
        exportPDF.exportpdf(response);

    }

}
