package com.example.emailverificationbackend.medicaldocs;

import java.time.LocalDateTime;


import com.example.emailverificationbackend.patient.Patient;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
@NoArgsConstructor @AllArgsConstructor @Builder
public class MedicalDocument {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @ManyToOne(optional = false) private Patient patient;
  @Enumerated(EnumType.STRING) private Doctype type;
  private String filename;
  private String storagePath;
  private LocalDateTime uploadedAt;
}
