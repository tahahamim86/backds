package com.example.emailverificationbackend.consultation;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;
@Data
@Entity
public class TypeActe {

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

   
    private Timestamp deletedAt;
    @OneToMany(mappedBy = "typeActe", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<HistoryActeMedical> historyActes;

}

