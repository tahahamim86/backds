package com.example.emailverificationbackend.medicalstructure;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
public class Structureoffer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private LocalDate begindate;
  
    private LocalDate enddate;
    @ManyToOne
    @JoinColumn(name = "offertype_id") 
    private Offertype offertype;

     @ManyToOne 
    @JoinColumn(name = "medicastructure_id") 
    private Medicalstructure mediastructure;


}
