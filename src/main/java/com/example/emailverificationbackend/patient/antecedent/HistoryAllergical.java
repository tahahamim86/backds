package com.example.emailverificationbackend.patient.antecedent;

import jakarta.persistence.Entity;
import lombok.Data;

import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.example.emailverificationbackend.patient.Patient;

import jakarta.persistence.*;
@Data
@Entity
@Table(name = "historyallergical")
public class HistoryAllergical {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "type_allergy_id")
    private AllergyType allergyType;
    private String date;
    private String observation;

}
