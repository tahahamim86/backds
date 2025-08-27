package com.example.emailverificationbackend.staffmedical;

import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.example.emailverificationbackend.appuser.AppUser;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "medicalstaff")
public class MedicalStaff {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "staffmedicaltype_id")
    private StaffMedicalType staffMedicalType;

    private String fullname;
    private Integer pays_id;
    private String birthday;
    private String placebirth;
    private String gender;
    private String identitymatricule;
    private String passport;
    private Integer speciality_id;
    private String longitude;
    private String latitude;
    private String t1belanguage_id;
    private Boolean activated;
    private Boolean authorised;
    private Boolean signaled;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private AppUser user;

    @CreationTimestamp
    private Timestamp createdAt;

    @UpdateTimestamp
    private Timestamp updatedAt;

    // Getters and setters
}
