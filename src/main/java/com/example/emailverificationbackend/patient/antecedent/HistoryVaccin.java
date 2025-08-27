package com.example.emailverificationbackend.patient.antecedent;

import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.example.emailverificationbackend.patient.Patient;

import jakarta.persistence.*;
import lombok.Data;
@Data
@Entity
@Table(name = "historyvaccin")
public class HistoryVaccin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "vaccinetype_id")
    private VaccineType vaccineType;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    private String datevaccin;
    private Integer nbrvaccin;
    private String observation;



    // Getters and setters
}
