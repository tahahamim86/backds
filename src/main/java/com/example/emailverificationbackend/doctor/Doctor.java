package com.example.emailverificationbackend.doctor;
import com.example.emailverificationbackend.appuser.AppUser;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Data;
@Data
@Entity
public class Doctor {
@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
private Long id;
@OneToOne(optional = false) private AppUser user;
private String specialite;
}
