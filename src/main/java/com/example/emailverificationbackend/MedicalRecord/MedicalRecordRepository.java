package com.example.emailverificationbackend.MedicalRecord;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface MedicalRecordRepository extends JpaRepository<MedicalRecord, Long> {

    // Find MedicalRecord by AppUser's ID
    Optional<MedicalRecord> findByAppUserId(Long userId);
    Optional<MedicalRecord> findByAppUserEmail(String email);

}
