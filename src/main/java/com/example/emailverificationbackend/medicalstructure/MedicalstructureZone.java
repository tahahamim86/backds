package com.example.emailverificationbackend.medicalstructure;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.example.emailverificationbackend.pays.Pays;

import jakarta.persistence.*;
import lombok.Data;
@Data
@Entity
public class MedicalstructureZone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String alias;
    private String description;
    @ManyToOne
    @JoinColumn(name = "pays_id")
    private Pays pays;
}
