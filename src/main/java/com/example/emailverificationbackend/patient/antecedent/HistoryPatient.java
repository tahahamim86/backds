package com.example.emailverificationbackend.patient.antecedent;

import jakarta.persistence.Entity;
import lombok.Data;

import com.example.emailverificationbackend.patient.Patient;
import com.example.emailverificationbackend.prescription.Prescription;

import jakarta.persistence.*;
@Data
@Entity
@Table(name = "historypatient")
public class HistoryPatient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "prescription_id")
    private Prescription prescription;

    private String maladie;

    // Getters and setters
}
