package com.example.emailverificationbackend.prescription;

import java.security.Timestamp;

import jakarta.persistence.*;
import lombok.Data;
@Data
@Entity
public class Paperclip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "referenceconsultation")
    private int referenceConsultation;
    @Column(name = "referencerapport")
    private String referenceRapport;
    private String description;
    private String daterapport;
    private String printdate;
    private String nbrprint;
    private boolean activated;
    @Column(name = "created_at")
    private Timestamp createdAt;
    @Column(name = "updated_at")
    private Timestamp updatedAt;

}
