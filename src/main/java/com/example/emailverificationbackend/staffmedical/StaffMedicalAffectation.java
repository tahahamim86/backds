package com.example.emailverificationbackend.staffmedical;

import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.example.emailverificationbackend.medicalstructure.Batiment;
import com.example.emailverificationbackend.medicalstructure.Etage;
import com.example.emailverificationbackend.medicalstructure.Medicalstructure;
import com.example.emailverificationbackend.medicalstructure.Office;
import com.example.emailverificationbackend.medicalstructure.Service;
import com.example.emailverificationbackend.medicalstructure.Site;

import jakarta.persistence.*;
import lombok.Data;
@Data
@Entity
@Table(name = "staffmedicalaffectation")
public class StaffMedicalAffectation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "medicalstructure_id")
    private Medicalstructure medicalStructure;
    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private MedicalStaff doctor;
    @ManyToOne
    @JoinColumn(name = "staff_medical_id")
    private MedicalStaff staffMedical;
    @ManyToOne
    @JoinColumn(name = "site_id")
    private Site site;
    @ManyToOne
    @JoinColumn(name = "batiment_id")
    private Batiment batiment;
    @ManyToOne
    @JoinColumn(name = "etage_id")
    private Etage etage;
    @ManyToOne
    @JoinColumn(name = "service_id")
    private Service service;
    @ManyToOne
    @JoinColumn(name = "office_id")
    private Office office;
    private String datebegin;
    private String dateend;
    private Integer emploiTemps_id;
    private Boolean activated;
    @CreationTimestamp
    private Timestamp createdAt;
    @UpdateTimestamp
    private Timestamp updatedAt;
}
