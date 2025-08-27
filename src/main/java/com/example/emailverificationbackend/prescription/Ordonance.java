package com.example.emailverificationbackend.prescription;

import com.example.emailverificationbackend.consultation.Consultationhistory;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Ordonance {
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
private Long id;
private String reference_ordonnance;
private String labe;
private String date_print;
private String nbprint;
private String description;
  @ManyToOne
    @JoinColumn(name = "consultationHistory_id")
    private Consultationhistory consultationHistory;
}
