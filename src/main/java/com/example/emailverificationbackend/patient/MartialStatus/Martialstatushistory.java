package com.example.emailverificationbackend.patient.MartialStatus;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
@Entity
@Data
public class Martialstatushistory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
   
    private LocalDate date;
}
