package com.example.emailverificationbackend.consultation;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;
@Data
@Entity
public class VisiteType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "codeacte", nullable = false)
    private String codeacte;

    @Column(name = "label", nullable = false)
    private String label;

    @Column(name = "realvalue", nullable = false)
    private Double realValue;

    @Column(name = "recovervalue", nullable = false)
    private Double recoverValue;

    @Column(name = "activated", nullable = false)
    private Boolean activated;



}
