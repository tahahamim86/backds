package com.example.emailverificationbackend.examen;

import java.sql.Timestamp;

import com.example.emailverificationbackend.consultation.Consultationhistory;

import jakarta.persistence.*;
import jakarta.persistence.GeneratedValue;
import lombok.Data;
@Data
@Entity
@Table(name = "examenpneumology")
public class ExamenPneumology {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "referenceconsultation", referencedColumnName = "referenceconsultation")
    private Consultationhistory consultationHistory;

    private String dyspnee;
    private String toux;
    private String hoquet;

   
}
