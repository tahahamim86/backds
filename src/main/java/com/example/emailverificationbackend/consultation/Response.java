package com.example.emailverificationbackend.consultation;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Response {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "referenceConsultation")
    private Consultationhistory consultationHistory;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;

    private Boolean accepted;
    private String instantArt;


}
