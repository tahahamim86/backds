package com.example.emailverificationbackend.patient.antecedent;

import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.*;
import lombok.Data;
@Entity
@Data
public class AllergyType {
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
private Long id;
private String alias;
private String description;
private boolean activated;


}
