package com.example.emailverificationbackend.patient.antecedent;

import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.*;
import lombok.Data;
@Data
@Entity
@Table(name = "vaccinetype")
public class VaccineType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer pays_id;
    private String alias;
    private String description;
    private Boolean activated;

    

}
