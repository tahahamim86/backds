package com.example.emailverificationbackend.examen;
import java.sql.Timestamp;

import com.example.emailverificationbackend.consultation.Consultationhistory;

import jakarta.persistence.*;
import lombok.Data;
@Data
@Entity
@Table(name = "examengastroscopy")
public class ExamenGynecologie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "referenceconsultation", referencedColumnName = "referenceconsultation")
    private Consultationhistory consultationHistory;
    private String exgeneralgyneco;
    private String experinealgyneco;
    private String expelvein;
 
}
