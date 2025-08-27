package com.example.emailverificationbackend.examen;

import java.sql.Timestamp;

import com.example.emailverificationbackend.consultation.Consultationhistory;

import jakarta.persistence.*;
import lombok.Data;
@Data
@Entity
@Table(name = "examencomplementophtalmology")
public class ExamenComplementOphtalmology {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "referenceconsultation", referencedColumnName = "referenceconsultation")
    private Consultationhistory consultationHistory;

    private String angiographiefluoresceine;
    private String angiographieindocyanine;    
    private String echographieA;
    private String echographieB;

}
