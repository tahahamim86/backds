package com.example.emailverificationbackend.patient.antecedent;

import jakarta.persistence.*;
import lombok.Data;
import java.util.*;

import com.example.emailverificationbackend.medicalstructure.Medicalstructure;
import com.example.emailverificationbackend.patient.Patient;
import com.example.emailverificationbackend.prescription.Prescription;
@Entity
@Data
public class Historychirugical {
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
private Long id;
@ManyToMany
@JoinTable(name = "patient")
private List<Patient> patients;
@ManyToMany
@JoinTable(name = "prescriotion")
private List<Prescription> prescriptions;
@ManyToOne
@JoinTable(name = "mediastructure")
private Medicalstructure medicalstructure;

}
