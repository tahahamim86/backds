package com.example.emailverificationbackend.examen;

import java.sql.Timestamp;

import com.example.emailverificationbackend.consultation.Consultationhistory;

import jakarta.persistence.*;
import lombok.Data;
@Data
@Entity
@Table(name = "examenophtalmology")
public class ExamenOphtalmology {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "referenceconsultation", referencedColumnName = "referenceconsultation")
    private Consultationhistory consultationHistory;

    private String oeilGaucherefract;
    private String oeilDroitrefract;
    private String oeildroitek1;
    private String oeildroitek2;
    private String oeilgauchek2;
    private String oeildroiterayon;


}
