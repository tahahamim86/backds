package com.example.emailverificationbackend.medical_diagnosis;

import com.example.emailverificationbackend.MedicalRecord.MedicalRecord;
import com.example.emailverificationbackend.MedicalRecord.MedicalRecordRepository;
import com.example.emailverificationbackend.appuser.AppUser;
import com.example.emailverificationbackend.appuser.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/diagnosis")
@CrossOrigin("*")  // Allow cross-origin requests if needed
public class DiseaseDiagnosisController {

    @Autowired
    private DiseaseDiagnosisServiceImpl diagnosisService;

    @Autowired
    private MedicalRecordRepository medicalRecordRepository;

    @Autowired
    private AppUserRepository appUserRepository;

    // Get a single disease diagnosis by ID
    @GetMapping("/{id}")
    public ResponseEntity<DiseaseDiagnosis> getDiagnosisById(@PathVariable Long id) {
        return ResponseEntity.ok(diagnosisService.getDiseaseDiagById(id));
    }

    
    @PostMapping("/add")
    public ResponseEntity<?> addDiagnosis(
            @RequestParam("diagnosis_Date") @DateTimeFormat(pattern = "yyyy-MM-dd") java.util.Date diagnosisDate,
            @RequestParam("disease_name") String diseaseName,
            @RequestParam("diagnostic_details") String diagnosticDetails,
            @RequestParam("image") MultipartFile imageFile,
            @RequestParam("email") String email) {
    
        try {
            // Validate inputs
            if (diagnosisDate == null || diseaseName == null || diagnosticDetails == null || imageFile.isEmpty() || email == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Missing required parameters");
            }
    
            // Fetch the AppUser by email
            Optional<AppUser> appUserOptional = appUserRepository.findByEmail(email);
            if (!appUserOptional.isPresent()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("No user found with email: " + email);
            }
            AppUser appUser = appUserOptional.get();
        
            // Check if MedicalRecord exists; if not, create one
            MedicalRecord medicalRecord = appUser.getMedicalRecord();
            if (medicalRecord == null) {
                medicalRecord = new MedicalRecord();
                medicalRecord.setAppUser(appUser);
                medicalRecordRepository.save(medicalRecord);
                appUser.setMedicalRecord(medicalRecord);
                appUserRepository.save(appUser);
            }
        
            // Convert image to byte array
            byte[] imageBytes = imageFile.getBytes();
        
            // Create new DiseaseDiagnosis
            DiseaseDiagnosis diagnosis = new DiseaseDiagnosis();
            diagnosis.setDiagnosis_Date(new java.sql.Date(diagnosisDate.getTime()));
            diagnosis.setDisease_name(diseaseName);
            diagnosis.setDiagnostic_details(diagnosticDetails);
            diagnosis.setImage(imageBytes);
            diagnosis.setMedicalRecord(medicalRecord);
        
            // Save the diagnosis
            DiseaseDiagnosis savedDiagnosis = diagnosisService.addDiagnosis(diagnosis);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedDiagnosis);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error processing image: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error: " + e.getMessage());
        }
    }
    

    // Delete a diagnosis by ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteDiagnosis(@PathVariable Long id) {
        diagnosisService.deleteDiagnosis(id);
        return ResponseEntity.ok("Diagnosis with ID " + id + " has been deleted.");
    }
    @GetMapping("/user/{email}")
    public ResponseEntity<?> getDiagnosesByUserEmail(@PathVariable String email) {
        Optional<MedicalRecord> medicalRecordOptional = medicalRecordRepository.findByAppUserEmail(email);
        if (!medicalRecordOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No medical record found for user with email: " + email);
        }
    
        MedicalRecord medicalRecord = medicalRecordOptional.get();
        List<Map<String, Object>> response = medicalRecord.getDiagnosis()
                .stream()
                .map(diagnosis -> {
                    Map<String, Object> diagnosisMap = new HashMap<>();
                    diagnosisMap.put("id", diagnosis.getId());
                    diagnosisMap.put("disease_name", diagnosis.getDisease_name());
                    diagnosisMap.put("diagnostic_details", diagnosis.getDiagnostic_details());
                    String base64Image = Base64.getEncoder().encodeToString(diagnosis.getImage());
                    diagnosisMap.put("image", base64Image);
                    return diagnosisMap;
                })
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }
    
}
