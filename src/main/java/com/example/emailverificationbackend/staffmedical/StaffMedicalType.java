package com.example.emailverificationbackend.staffmedical;

import jakarta.persistence.Entity;
import lombok.Data;

import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.*;
@Data
@Entity
@Table(name = "staffmedicaltype")
public class StaffMedicalType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String label;
    private String alias;
    private String description;
    private Boolean activated;

    @CreationTimestamp
    private Timestamp createdAt;

    @UpdateTimestamp
    private Timestamp updatedAt;
}
