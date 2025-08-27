package com.example.emailverificationbackend.prescription;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
public class Detailsorder {
  @Id
@GeneratedValue(strategy = GenerationType.AUTO)
private Long id;
private String dosage;
private String nbr;
private String period;
private String duration;
  @ManyToOne
    @JoinColumn(name = "prescription_id") // Ensure this matches your database schema
    private Prescription prescription;

}
