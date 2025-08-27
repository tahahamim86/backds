package com.example.emailverificationbackend.medicalstructure;

import java.util.*;
import jakarta.persistence.*;
import lombok.Data;
@Data
@Entity
@Table(name = "batiment")
public class Batiment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "label")
    private String label;

    @Column(name = "alias")
    private String alias;

    @Column(name = "description")
    private String description;
 @OneToMany(mappedBy = "batiment", cascade = CascadeType.ALL)
    private List<Etage> etages; 
    @Column(name = "activated")
    private boolean activated;
    @ManyToOne
    @JoinColumn(name = "site_id")
    private Site site;
}
