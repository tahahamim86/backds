package com.example.emailverificationbackend.pays;

import java.util.List;

import com.example.emailverificationbackend.medicalstructure.Site;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;
@Entity
@Data
public class Pays {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String alias;
    private String flag;
    @OneToMany(mappedBy = "pays") 
    private List<Site> sites; 
    @ManyToOne
    @JoinColumn(name = "zone_id") 
    private Zone zone;
}
