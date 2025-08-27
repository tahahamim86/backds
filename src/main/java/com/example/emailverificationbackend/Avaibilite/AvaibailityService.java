package com.example.emailverificationbackend.Avaibilite;


import org.springframework.stereotype.Service;
import com.example.emailverificationbackend.doctor.Doctor;
import com.example.emailverificationbackend.doctor.DoctorRepository;

import lombok.RequiredArgsConstructor;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AvaibailityService {

   private final AvaibailityRepository avaibailityRepository;
    private final DoctorRepository doctorRepository;

    public Avaibaility addAvailability(Long doctorUserId, DayOfWeek day, LocalTime start, LocalTime end) {
        Doctor doctor = doctorRepository.findByUserId(doctorUserId)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));

        Avaibaility availability = new Avaibaility();
        availability.setMedecin(doctor);
        availability.setJour(day);
        availability.setDebut(start);
        availability.setFin(end);

        return avaibailityRepository.save(availability);
    }

    public List<Avaibaility> getDoctorAvailability(Long doctorUserId) {
        Doctor doctor = doctorRepository.findById(doctorUserId)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));

        return avaibailityRepository.findByMedecin(doctor);
    }
}
