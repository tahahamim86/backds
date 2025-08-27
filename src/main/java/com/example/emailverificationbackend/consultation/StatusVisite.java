package com.example.emailverificationbackend.consultation;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class StatusVisite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "alias", nullable = false)
    private String alias;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "activated", nullable = false)
    private Boolean activated;


}
