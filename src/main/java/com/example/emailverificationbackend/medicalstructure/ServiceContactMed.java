package com.example.emailverificationbackend.medicalstructure;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class ServiceContactMed {

    @Id
    @GeneratedValue(strategy =GenerationType.AUTO)
    private Long id;
    private String contact;
    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="medicalstructure_id",referencedColumnName = "id")
    private Medicalstructure medicalstructure;
    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="service_id",referencedColumnName = "id")
    private Service service;
    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="typecontactmedicals_id",referencedColumnName = "id")
    private TypeContactMedicalS typeContactMedicalS;
}
