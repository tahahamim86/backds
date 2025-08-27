package com.example.emailverificationbackend.prescription;

import java.sql.Timestamp;

import com.example.emailverificationbackend.consultation.Consultationhistory;

import jakarta.persistence.*;
import lombok.Data;
@Data
@Entity
public class Certificate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "consultation_history_id") // Foreign key
    private Consultationhistory consultationHistory;
    @Column(name = "referencecertificate")
    private String referenceCertificate;

    @Column(name = "datecertificate")
    private String dateCertificate;

    private String description;
    private String printdate;
    private String nbrprint;
    private boolean activated;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @Column(name = "updated_at")
    private Timestamp updatedAt;

    @Column(name = "deleted_at")
    private Timestamp deletedAt;

}
