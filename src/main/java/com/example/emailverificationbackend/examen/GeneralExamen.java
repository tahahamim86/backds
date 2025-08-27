package com.example.emailverificationbackend.examen;

import java.sql.Timestamp;

import com.example.emailverificationbackend.consultation.Consultationhistory;

import jakarta.persistence.*;
import lombok.Data;
@Data
@Entity
@Table(name = "generalexamen")
public class GeneralExamen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "referenceconsultation", referencedColumnName = "referenceconsultation")
    private Consultationhistory consultationHistory;

    private String poids;
    private String taille;
    private String bmi;
    private String temperature;
    private String tensionArterielle;
    private int heartrating;


}
