package com.example.emailverificationbackend.consultation;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.example.emailverificationbackend.doctor.Doctor;
import com.example.emailverificationbackend.doctor.DoctorRepository;
import com.example.emailverificationbackend.patient.Patient;
import com.example.emailverificationbackend.patient.PatientRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class AppointementService {
private final AppointementRepository repo;
  private final PatientRepository patients;
  private final DoctorRepository medecins;

  public Appointment book(Long patientUserId, Long medecinId, LocalDateTime when, String notes){
    Patient p = patients.findById(patientUserId).orElseThrow();
    Doctor d = medecins.findById(medecinId).orElseThrow();
    Appointment a = Appointment.builder().patient(p).medecin(d).date(when).notes(notes).status(AppointementStatus.EN_ATTENTE).build();
    return repo.save(a);
  }

  public List<Appointment> forPatient(Long patientUserId){
    Patient p = patients.findById(patientUserId).orElseThrow();
    return repo.findByPatientId(p.getId());
  }

  public List<Appointment> forMedecin(Long medecinUserId){
    Doctor d = medecins.findByUserId(medecinUserId).orElseThrow();
    return repo.findByMedecinId(d.getId());
  }

  public Appointment validate(Long appointmentId){
    Appointment a = repo.findById(appointmentId).orElseThrow();
    a.setStatus(AppointementStatus.CONFIRME);
    return repo.save(a);
}
}