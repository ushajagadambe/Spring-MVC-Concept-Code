package com.example.curdoperation.curdoperation.domain;


import lombok.Data;

import javax.persistence.*;
@Data
@Entity
@Table(name="hospital")
public class Hospital {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", updatable = false)
    private long id;
    private String hospitalName;
    private String location;

    public Hospital() {
    }

    public Hospital(String hospitalName, String location) {
        this.hospitalName = hospitalName;
        this.location = location;
    }
}
