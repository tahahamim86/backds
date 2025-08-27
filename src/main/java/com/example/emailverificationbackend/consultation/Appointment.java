package com.example.emailverificationbackend.consultation;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.example.emailverificationbackend.doctor.Doctor;
import com.example.emailverificationbackend.patient.Patient;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Entity @NoArgsConstructor @AllArgsConstructor @Builder
public class Appointment {
     @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private LocalDateTime date;
  @Enumerated(EnumType.STRING)
  private AppointementStatus status = AppointementStatus.EN_ATTENTE;
  @ManyToOne(optional = false) private Patient patient;
  @ManyToOne(optional = false) private Doctor medecin;
  @Column(length = 2000) private String notes;


}
