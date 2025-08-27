package com.example.emailverificationbackend.doctor;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


public interface DoctorRepository extends JpaRepository<Doctor,Long>{
  Optional<Doctor> findByUserId(Long userId);
}
