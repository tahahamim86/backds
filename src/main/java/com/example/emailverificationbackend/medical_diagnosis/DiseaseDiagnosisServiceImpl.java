package com.example.emailverificationbackend.medical_diagnosis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class DiseaseDiagnosisServiceImpl implements DiseaseDiagnosisService {

    @Autowired
    private DiseaseDiagnosticRepo diagnosisRepository;  // Inject repository

    // ✅ Get all diagnoses
    @Override
    public List<DiseaseDiagnosis> getAllDiagnosis() {
        return diagnosisRepository.findAll();
    }
 

    // Get all disease diagnoses for a specific user
    public List<DiseaseDiagnosis> getDiagnosesByUserId(Long userId) {
        return diagnosisRepository.findByMedicalRecordAppUserId(userId);
    }
    // ✅ Get a single diagnosis by ID
    @Override
    public DiseaseDiagnosis getDiseaseDiagById(Long id) {
        return diagnosisRepository.findById(id)
                .orElseThrow(() -> new DiseaseNotFoundException("Diagnosis not found with ID: " + id));
    }

    // ✅ Add a new diagnosis
    @Override
    public DiseaseDiagnosis addDiagnosis(DiseaseDiagnosis diagnosis) {
        if (diagnosis == null) {
            throw new IllegalArgumentException("Diagnosis cannot be null.");
        }
        return diagnosisRepository.save(diagnosis);
    }


    // ✅ Delete a diagnosis by ID
    @Override
    public void deleteDiagnosis(Long id) {
        if (!diagnosisRepository.existsById(id)) {
            throw new DiseaseNotFoundException("Diagnosis not found with ID: " + id);
        }
        diagnosisRepository.deleteById(id);
    }

    public List<DiseaseDiagnosis> getDiagnosisByMedicalRecordId(Long medicalRecordId) {
        return diagnosisRepository.findByMedicalRecordId(medicalRecordId);
    }
}
