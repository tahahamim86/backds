package com.example.emailverificationbackend.MedicalRecord;

import jakarta.persistence.*;
import lombok.Data;
import java.util.ArrayList;
import java.util.List;
import com.example.emailverificationbackend.appuser.AppUser;
import com.example.emailverificationbackend.medical_diagnosis.DiseaseDiagnosis;
import com.example.emailverificationbackend.patient.Patient;
import com.fasterxml.jackson.annotation.JsonManagedReference;
@Entity
@Data
public class MedicalRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "app_user_id", referencedColumnName = "id", unique = true, nullable = false)
    private AppUser appUser;

    @OneToMany(mappedBy = "medicalRecord", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<DiseaseDiagnosis> diagnosis = new ArrayList<>();

    @OneToOne(optional = false)
    private Patient patient;  // Remove @Id here!

    @Column(length = 4000)
    private String antecedents;

    @Column(length = 4000)
    private String allergies;

    @Column(length = 4000)
    private String traitements;

    // ... setters/getters
}
