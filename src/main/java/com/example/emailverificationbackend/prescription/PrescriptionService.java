package com.example.emailverificationbackend.prescription;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.example.emailverificationbackend.doctor.Doctor;
import com.example.emailverificationbackend.doctor.DoctorRepository;
import com.example.emailverificationbackend.patient.Patient;
import com.example.emailverificationbackend.patient.PatientRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PrescriptionService {
private final PrescriptionRepository prescriptionRepo;
  private final DoctorRepository doctorRepo;
  private final PatientRepository patientRepo;

  // Method to create a prescription for a patient
  public Prescription createPrescription(Long doctorUserId, Long patientUserId, String contenu) {
    // Find the doctor by user ID
    Doctor doctor = doctorRepo.findByUserId(doctorUserId).orElseThrow(() -> new RuntimeException("Doctor not found"));
    
    // Find the patient by user ID
    Patient patient = patientRepo.findById(patientUserId).orElseThrow(() -> new RuntimeException("Patient not found"));
    
    // Create the prescription
    Prescription prescription = Prescription.builder()
        .medecin(doctor)
        .patient(patient)
        .date(LocalDateTime.now()) // Set the current date
        .contenu(contenu) // Prescription content
        .build();
    
    // Save the prescription to the database
    return prescriptionRepo.save(prescription);
  }
}