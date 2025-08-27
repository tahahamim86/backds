package com.example.emailverificationbackend.examen;
import java.sql.Timestamp;

import com.example.emailverificationbackend.consultation.Consultationhistory;

import jakarta.persistence.*;
import lombok.Data;
@Data
@Entity
@Table(name = "examencomplementgynecology")
public class ExamenComplementGynecology {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "referenceconsultation", referencedColumnName = "referenceconsultation")
    private Consultationhistory consultationHistory;

    private String biopsiecol;
    private String biopsieendometre;
    private String biopsievulvairevaginale;
    private String vulvoscopie;

   
}
