package com.example.emailverificationbackend.examen;
import java.sql.Timestamp;

import com.example.emailverificationbackend.consultation.Consultationhistory;

import jakarta.persistence.*;
import lombok.Data;
@Data
@Entity
@Table(name = "examenpneumologie")
public class ExamenComplementPneumology {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "referenceconsultation", referencedColumnName = "referenceconsultation")
    private Consultationhistory consultationHistory;

    private String debitexpiratoirepointe;
    private String respiratoiresspirometrie;
    private String respiratoiresplethysmographie;
    private String interpretation_epreuves_froncresp;

}
