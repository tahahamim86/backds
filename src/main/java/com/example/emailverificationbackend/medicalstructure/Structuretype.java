package com.example.emailverificationbackend.medicalstructure;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
@Entity
@Data
public class Structuretype {
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
private Long id;
private String name;
private String alias;
private String description;

}
