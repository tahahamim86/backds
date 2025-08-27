package com.example.emailverificationbackend.examen;
import java.sql.Timestamp;

import com.example.emailverificationbackend.consultation.Consultationhistory;

import jakarta.persistence.*;
import lombok.Data;
@Data
@Entity
@Table(name = "examendentist")
public class ExamenDentist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "referenceconsultation", referencedColumnName = "referenceconsultation")
    private Consultationhistory consultationHistory;
    private String maladiegencive;
    private String dentconserver;
    private String carredent;
    private String gencive;
    private String plonbage;
    private String carie;
    private String dentagresse;
    private String autres;
    private String paradentile;
    private String anesthesie;
    private String diabet;
    private String grossesse;
    private String tabac;
    private String dentprimaire;
    private String opneesomeil;
    private String hta;
    private String cardiaque;
    private String orrificebucallevre;
    private String langue;
    private String freinslevrelangue;
    private String paradonte;
    private String glandessalivaires;
    private String articulation;
    private String innervationfaciale;
    private String aires;
    private String malaidiecavite;
    private String remarque;
    private String radio;
    private String fracture;
    private String tartre;

}
