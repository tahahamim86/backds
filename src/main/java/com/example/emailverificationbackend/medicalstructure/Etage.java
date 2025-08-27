package com.example.emailverificationbackend.medicalstructure;


import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Entity
@Data
public class Etage {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private int number;  // Floor number

    @ManyToOne
    @JoinColumn(name = "batiment_id")
    private Batiment batiment;  // Many floors can belong to one building

    @OneToMany(mappedBy = "etage", cascade = CascadeType.ALL)
    private List<Office> offices;  // List of offices on the floor
}

