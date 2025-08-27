package com.example.emailverificationbackend.MedicalRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class MedicalRecordService {

    @Autowired
    private MedicalRecordRepository medicalRecordRepository;

    public Optional<MedicalRecord> getMedicalRecordByAppUserId(Long appUserId) {
        return medicalRecordRepository.findByAppUserId(appUserId);
    }
}
