package com.example.emailverificationbackend.medicalstructure;

import com.example.emailverificationbackend.pays.Pays;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Site {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;
@ManyToOne 
@JoinColumn(name = "pay_id")
private Pays pays; 
@OneToOne(mappedBy = "site") 
private Medicalstructure medicalStructure;
private String label;
private String alias;
private String description;




}
