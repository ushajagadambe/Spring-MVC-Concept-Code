package com.example.curdoperation.curdoperation.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HospitalService {
    @Autowired
    HospitalRepository hospitalRepository;
    public List<Hospital> getAllList()
    {
        List<Hospital> list=new ArrayList<Hospital>();
        list=hospitalRepository.findAll();
        return list;
    }
    public Hospital saveHospital(Hospital hospital){
       return hospitalRepository.saveAndFlush(hospital);
    }
}
