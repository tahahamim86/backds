package com.example.emailverificationbackend.auth.token;

import com.example.emailverificationbackend.appuser.AppUser;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class PasswordResetToken {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private String token;
    
    @ManyToOne
    @JoinColumn(name = "app_user_id")
    private AppUser appUser;
    
    private LocalDateTime expiresAt;
    
    private LocalDateTime createdAt;

    // Constructor to initialize PasswordResetToken with necessary fields
    public PasswordResetToken(String token, AppUser appUser, LocalDateTime expiresAt) {
        this.token = token;
        this.appUser = appUser;
        this.expiresAt = expiresAt;
        this.createdAt = LocalDateTime.now();
    }
}
