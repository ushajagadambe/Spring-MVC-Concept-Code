package com.example.pdf.Pdfgeneration;

import com.example.pdf.Pdfgeneration.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HospitalRepository extends JpaRepository<Hospital, Long> {
}