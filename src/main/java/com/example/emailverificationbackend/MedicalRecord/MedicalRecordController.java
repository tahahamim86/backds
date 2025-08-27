package com.example.emailverificationbackend.MedicalRecord;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.emailverificationbackend.medical_diagnosis.DiseaseDiagnosisServiceImpl;

import java.util.Optional;

@RestController
@RequestMapping("/api/medical-records")
@CrossOrigin("*")
public class MedicalRecordController {

    @Autowired
    private MedicalRecordService medicalRecordService;

    @Autowired
    private DiseaseDiagnosisServiceImpl diagnosisService;

    // ✅ Get medical record by AppUser ID
    @GetMapping("/user/{appUserId}")
    public ResponseEntity<MedicalRecord> getMedicalRecordByUserId(@PathVariable Long appUserId) {
        Optional<MedicalRecord> medicalRecord = medicalRecordService.getMedicalRecordByAppUserId(appUserId);
        return medicalRecord.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // ✅ Get diagnoses by MedicalRecord ID
    @GetMapping("/{medicalRecordId}/diagnoses")
    public ResponseEntity<?> getDiagnosesByMedicalRecord(@PathVariable Long medicalRecordId) {
        return ResponseEntity.ok(diagnosisService.getDiagnosisByMedicalRecordId(medicalRecordId));
    }
}
