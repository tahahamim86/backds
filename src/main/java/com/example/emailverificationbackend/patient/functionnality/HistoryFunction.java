package com.example.emailverificationbackend.patient.functionnality;

import java.time.LocalDate;

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
public class HistoryFunction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate begindate;
    private LocalDate enddate;
    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="FunctionType_id",referencedColumnName = "id")
    private FunctionType functionType; 
    
}
