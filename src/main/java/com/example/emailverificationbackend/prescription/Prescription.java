package com.example.emailverificationbackend.prescription;


import java.time.LocalDateTime;

import com.example.emailverificationbackend.doctor.Doctor;
import com.example.emailverificationbackend.patient.Patient;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Prescription {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private LocalDateTime date;
  @ManyToOne(optional = false) private Doctor medecin;
  @ManyToOne(optional = false) private Patient patient;
  @Column(length = 4000) private String contenu; // texte simple pour MVP

}
