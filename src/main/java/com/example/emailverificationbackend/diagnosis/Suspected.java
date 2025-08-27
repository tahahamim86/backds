package com.example.emailverificationbackend.diagnosis;

import com.example.emailverificationbackend.consultation.Consultationhistory;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Suspected {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String referenceConsultation;
    private String diagnostic;



    @ManyToOne
    @JoinColumn(name = "consultationHistory_id")
    private Consultationhistory consultationHistory;

}

