package com.example.emailverificationbackend.staffmedical;

import java.sql.Timestamp;

import com.example.emailverificationbackend.medicalstructure.Medicalstructure;

import jakarta.persistence.*;
import lombok.Data;
@Data
@Entity
@Table(name = "contactoffice")
public class ContactOffice {   
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "typecontact_id")
    private TypeMedicalContact typeContact;
    @ManyToOne
    @JoinColumn(name = "medicalstructure_id")
    private Medicalstructure medicalStructure;
    private String contact;
    @Column(name = "created_at")
    private Timestamp createdAt;
    @Column(name = "updated_at")
    private Timestamp updatedAt;
    @Column(name = "deleted_at")
    private Timestamp deletedAt;
}

