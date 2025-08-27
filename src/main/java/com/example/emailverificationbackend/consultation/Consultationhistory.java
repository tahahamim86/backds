package com.example.emailverificationbackend.consultation;
import com.example.emailverificationbackend.diagnosis.DiagnosticDefinitif;
import com.example.emailverificationbackend.diagnosis.DiagnosticDifferentiel;
import com.example.emailverificationbackend.diagnosis.Suspected;
import com.example.emailverificationbackend.doctor.Doctor;
import com.example.emailverificationbackend.medicalstructure.Medicalstructure;
import com.example.emailverificationbackend.patient.Patient;
import com.example.emailverificationbackend.prescription.Certificate;
import com.example.emailverificationbackend.prescription.Ordonance;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
@Data
@Entity

public class Consultationhistory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
  
    private String speciality;
    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="doctor_id",referencedColumnName = "id")
    private Doctor doctor;
    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="patient_id",referencedColumnName = "id")
    private Patient patient;
    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="medicaltructure_id",referencedColumnName = "id")
    private Medicalstructure mediastructure;
    private String referenceconsultation;
   
    private LocalDateTime dateconsultation;
    private String motifconsultation;
    private String historyconsultation;
      @OneToMany(mappedBy = "consultationHistory", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Suspected> suspectedDiagnostics;

    @OneToMany(mappedBy = "consultationHistory", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<DiagnosticDefinitif> diagnosticDefinitifs;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "consultationHistory", fetch = FetchType.EAGER)
    private List<Ordonance> ordonances;
    @OneToMany(mappedBy = "consultationHistory", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<DiagnosticDifferentiel> diagnosticDifferentials;
    @OneToMany(mappedBy = "consultationHistory")
    private Set<Certificate> certificates;



 
    

}
