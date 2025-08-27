package com.example.emailverificationbackend.consultation;

import com.example.emailverificationbackend.doctor.Doctor;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String question;
    private String description;
    
    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    private Integer nbrQuestion;
    private Boolean activated;
    


    // getters and setters
}
