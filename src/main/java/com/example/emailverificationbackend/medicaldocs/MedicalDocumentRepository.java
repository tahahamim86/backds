package com.example.emailverificationbackend.medicaldocs;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface MedicalDocumentRepository extends JpaRepository<MedicalDocument,Long> {
List<MedicalDocument> findByPatientId(Long patientId);

}
