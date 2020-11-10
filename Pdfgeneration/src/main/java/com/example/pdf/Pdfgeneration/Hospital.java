package com.example.pdf.Pdfgeneration;

import lombok.Data;

import javax.persistence.*;
@Entity
@Table(name="hospital")
public class Hospital {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", updatable = false)
    private long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    private String hospitalName;
    private String location;

    public Hospital() {
    }

    public Hospital(String hospitalName, String location) {
        this.hospitalName = hospitalName;
        this.location = location;
    }
}