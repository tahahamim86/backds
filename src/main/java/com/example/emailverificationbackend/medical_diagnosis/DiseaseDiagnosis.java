package com.example.emailverificationbackend.medical_diagnosis;

import com.example.emailverificationbackend.MedicalRecord.MedicalRecord;
import jakarta.persistence.*;
import lombok.Data;
import java.sql.Date;
import com.fasterxml.jackson.annotation.JsonBackReference;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
@Entity
@Data
public class DiseaseDiagnosis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date diagnosis_Date;
    private String disease_name;
    @Lob
    @JdbcTypeCode(SqlTypes.BINARY)
    @Column(nullable = false, columnDefinition = "BYTEA")
    private byte[] image;
    private String diagnostic_details;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "medical_record_id", nullable = false)
    @JsonBackReference  // Prevents infinite recursion
    private MedicalRecord medicalRecord;
}
