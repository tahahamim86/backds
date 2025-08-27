package com.example.emailverificationbackend.patient.aptitude;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class AptitudeType {
@Id
private Long id;
private String alias;
private String description;
private boolean activated;
}
