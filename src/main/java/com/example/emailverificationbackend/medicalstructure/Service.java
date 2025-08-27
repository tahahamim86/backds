package com.example.emailverificationbackend.medicalstructure;
import jakarta.persistence.*;
import lombok.Data;
@Entity
@Data
public class Service {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "site_id")  
    private Site site;
    @ManyToOne
    @JoinColumn(name = "employe_temps_id") 
    private Emploitemps employeTemps;
    private String label;
    private String alias;
    private String description;
}
