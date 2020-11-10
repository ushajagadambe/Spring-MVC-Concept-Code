package com.example.curdoperation.curdoperation.domain;

import com.itextpdf.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/hospital")
public class HospitalController {
    @Autowired
    HospitalService hospitalService;
    @PostMapping("/saveHospital/{hospitalName}/{location}")
    public ResponseEntity<Hospital> saveHospital(@PathVariable("hospitalName") String hospitalName, @PathVariable("location") String location) {
      Hospital hospital=new Hospital(hospitalName,location);
       Hospital hospitalData= hospitalService.saveHospital(hospital);
        return new ResponseEntity<Hospital>(hospitalData, HttpStatus.OK);
    }
    @GetMapping("/getAllHospital")
    public ResponseEntity<List<Hospital>> saveStudent() {
        List<Hospital>  hospitalList = hospitalService.getAllList();
        return new ResponseEntity<List<Hospital>>(hospitalList, HttpStatus.OK);
    }


}
