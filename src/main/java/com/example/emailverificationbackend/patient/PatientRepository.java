package com.example.emailverificationbackend.patient;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository  extends JpaRepository<Patient, Long> {


}
