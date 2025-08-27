package com.example.emailverificationbackend.patient.antecedent;

import jakarta.persistence.Entity;

import com.example.emailverificationbackend.patient.Patient;
import com.example.emailverificationbackend.prescription.Prescription;

import jakarta.persistence.*;
import jakarta.persistence.Id;
import lombok.Data;
@Data
@Entity
public class HistoryMedical {
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
private Long id;
@ManyToOne
@JoinColumn(name = "patient_id")
private Patient patient;

@ManyToOne
@JoinColumn(name = "prescription_id")
private Prescription prescription;


}
