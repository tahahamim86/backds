package com.example.emailverificationbackend.medical_diagnosis;

public class DiseaseNotFoundException extends RuntimeException {
    public DiseaseNotFoundException(String message) {
        super(message);
    }
}
