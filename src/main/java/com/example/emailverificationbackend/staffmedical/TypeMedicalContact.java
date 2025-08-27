package com.example.emailverificationbackend.staffmedical;

import java.sql.Timestamp;

import jakarta.persistence.*;
import lombok.Data;
@Data
@Entity
public class TypeMedicalContact {
@Id
@GeneratedValue(strategy=GenerationType.AUTO)
private Long id;
private String description;
private String alias;
private boolean activated;
@Column(name = "created_at")
private Timestamp createdAt;

@Column(name = "updated_at")
private Timestamp updatedAt;

@Column(name = "deleted_at")
private Timestamp deletedAt;
}
