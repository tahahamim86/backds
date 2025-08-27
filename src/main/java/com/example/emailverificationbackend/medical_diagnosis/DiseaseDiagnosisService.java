package com.example.emailverificationbackend.medical_diagnosis;

import java.util.List;

public interface DiseaseDiagnosisService {

   List<DiseaseDiagnosis> getAllDiagnosis();

   List<DiseaseDiagnosis> getDiagnosesByUserId(Long userId);
   DiseaseDiagnosis getDiseaseDiagById(Long id);
    
   DiseaseDiagnosis addDiagnosis(DiseaseDiagnosis diagnosis);
    

    
   void deleteDiagnosis(Long id); 
}
