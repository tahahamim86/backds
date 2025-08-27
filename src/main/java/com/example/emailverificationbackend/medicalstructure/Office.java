package com.example.emailverificationbackend.medicalstructure;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Office {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String officeNumber; 

    @ManyToOne
    @JoinColumn(name = "etage_id")
    private Etage etage; 
}
