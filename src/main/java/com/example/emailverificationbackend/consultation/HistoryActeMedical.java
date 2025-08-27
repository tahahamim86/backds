package com.example.emailverificationbackend.consultation;

import jakarta.persistence.*;
import java.sql.Timestamp;

@Entity
public class HistoryActeMedical {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "referenceconsultation", nullable = false)
    private Consultationhistory consultationHistory;

    @ManyToOne
    @JoinColumn(name = "typeacte_id", nullable = false)
    private TypeActe typeActe;

    @Column(name = "activated", nullable = false)
    private Boolean activated;

    @Column(name = "created_at", nullable = false)
    private Timestamp createdAt;

    @Column(name = "updated_at")
    private Timestamp updatedAt;

    @Column(name = "deleted_at")
    private Timestamp deletedAt;

}
