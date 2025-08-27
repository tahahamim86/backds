package com.example.emailverificationbackend.patient.antecedent;

import com.example.emailverificationbackend.patient.Patient;
import com.example.emailverificationbackend.prescription.Prescription;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
    @Table(name = "historieepoux")
    public class HistoryEpoux {
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
    
    }
    