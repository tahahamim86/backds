package com.example.emailverificationbackend.patient.aptitude;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Data;
@Entity
@Data
public class HistoryAptitude {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private LocalDateTime begindate;
    private int nbraptitude;
    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="aptitudetype_id",referencedColumnName = "id")
    private AptitudeType aptitudeType; 
    
}
