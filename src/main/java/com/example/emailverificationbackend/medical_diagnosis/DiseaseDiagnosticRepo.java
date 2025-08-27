package com.example.emailverificationbackend.medical_diagnosis;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DiseaseDiagnosticRepo extends JpaRepository<DiseaseDiagnosis,Long> {
    List<DiseaseDiagnosis> findByMedicalRecordId(Long medicalRecordId);
        // Query to find all diagnoses for a specific user
        List<DiseaseDiagnosis> findByMedicalRecordAppUserId(Long userId);

}
