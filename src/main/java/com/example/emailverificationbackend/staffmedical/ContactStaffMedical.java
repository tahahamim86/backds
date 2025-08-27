package com.example.emailverificationbackend.staffmedical;

import java.sql.Timestamp;

import jakarta.persistence.*;
import lombok.Data;
@Data
@Entity
@Table(name = "contactstaffmedical")
public class ContactStaffMedical {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "medicalstaff_id")
    private MedicalStaff medicalStaff;

    @ManyToOne
    @JoinColumn(name = "typecontact_id")
    private TypeMedicalContact typeContact;

    private String contact;
    private boolean activated;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @Column(name = "updated_at")
    private Timestamp updatedAt;

    @Column(name = "deleted_at")
    private Timestamp deletedAt;

}
