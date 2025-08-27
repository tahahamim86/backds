package com.example.emailverificationbackend.patient.MartialStatus;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Data;
@Entity
@Data
public class MartialStatus {

    @Id
    @GeneratedValue(strategy =GenerationType.AUTO)
    private Long id;
    private String alias;
    private String description;
    private boolean activated;   
    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="martialstatushistory_id",referencedColumnName = "id")
    private Martialstatushistory martialstatushistory; 
}
