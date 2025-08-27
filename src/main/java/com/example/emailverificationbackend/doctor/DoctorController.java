package com.example.emailverificationbackend.doctor;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.emailverificationbackend.Avaibilite.Avaibaility;
import com.example.emailverificationbackend.Avaibilite.AvaibailityService;
import com.example.emailverificationbackend.consultation.AppointementService;
import com.example.emailverificationbackend.consultation.Appointment;
import com.example.emailverificationbackend.prescription.Prescription;
import com.example.emailverificationbackend.prescription.PrescriptionService;

import lombok.RequiredArgsConstructor;
@RequiredArgsConstructor
@RestController
@RequestMapping("/doctor/")
public class DoctorController {
  private final AppointementService appointmentService;
  private final PrescriptionService prescriptionService;
private final AvaibailityService avaibailityService;

  @GetMapping("/{medecinUserId}/appointments")
  public ResponseEntity<List<Appointment>> getAppointments(@PathVariable Long medecinUserId) {
    List<Appointment> appointments = appointmentService.forMedecin(medecinUserId);
    return ResponseEntity.ok(appointments);
  }

  @PostMapping("/{medecinUserId}/appointments/validate/{appointmentId}")
  public ResponseEntity<Appointment> validateAppointment(@PathVariable Long appointmentId) {
    Appointment appointment = appointmentService.validate(appointmentId);
    return ResponseEntity.ok(appointment);


}

  // Endpoint for the doctor to create a prescription for a patient
  @PostMapping("/{doctorUserId}/prescriptions")
  public ResponseEntity<Prescription> createPrescription(@PathVariable Long doctorUserId,
                                                         @RequestParam Long patientUserId, 
                                                         @RequestParam String contenu) {
    Prescription prescription = prescriptionService.createPrescription(doctorUserId, patientUserId, contenu);
    return ResponseEntity.ok(prescription);
                                                         }
@PostMapping("/{doctorUserId}/availability")
public ResponseEntity<Avaibaility> addAvailability(@PathVariable Long doctorUserId,
                                                   @RequestParam DayOfWeek day,
                                                   @RequestParam String start,
                                                   @RequestParam String end) {
    Avaibaility availability = avaibailityService.addAvailability(
            doctorUserId,
            day,
            LocalTime.parse(start),
            LocalTime.parse(end)
    );
    return ResponseEntity.ok(availability);
}

@GetMapping("/{doctorUserId}/availability")
public ResponseEntity<List<Avaibaility>> getAvailability(@PathVariable Long doctorUserId) {
    return ResponseEntity.ok(avaibailityService.getDoctorAvailability(doctorUserId));
}

}
