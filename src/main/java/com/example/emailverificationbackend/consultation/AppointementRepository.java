package com.example.emailverificationbackend.consultation;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AppointementRepository extends JpaRepository<Appointment,Long> {
 List<Appointment> findByPatientId(Long patientId);
  List<Appointment> findByMedecinId(Long medecinId);
}
