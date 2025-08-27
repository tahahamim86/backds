package com.example.emailverificationbackend.Avaibilite;

import java.time.DayOfWeek;
import java.time.LocalTime;

import com.example.emailverificationbackend.doctor.Doctor;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Avaibaility {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @ManyToOne(optional = false) private Doctor medecin;
  private DayOfWeek jour;
  private LocalTime debut;
  private LocalTime fin;
}
