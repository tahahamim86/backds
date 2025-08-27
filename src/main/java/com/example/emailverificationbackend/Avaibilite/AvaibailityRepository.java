package com.example.emailverificationbackend.Avaibilite;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.emailverificationbackend.doctor.Doctor;

public interface AvaibailityRepository extends JpaRepository<Avaibaility,Long> {
  List<Avaibaility> findByMedecinId(Long medecinId);
    List<Avaibaility> findByMedecin(Doctor doctor);
}
