package com.example.emailverificationbackend.medicalstructure;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Set;

@Entity
@Data
public class Emploitemps {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String monday;
    private String tuesday;
    private String wednesday;
    private String thursday;
    private String friday;
    private String saturday; 
    private String sunday; 
    
    private String workBegin; 
    private String workEnd;
    private String level;
    private String alias; 
    private String description;
    @OneToMany(mappedBy = "employeTemps") // Ensure this matches the property name in Service
    private Set<Service> services;
}
