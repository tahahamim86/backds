package com.example.emailverificationbackend.auth.token;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder; // Import BCryptPasswordEncoder
import org.springframework.stereotype.Service;

import com.example.emailverificationbackend.appuser.AppUser;
import com.example.emailverificationbackend.appuser.AppUserRepository;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PasswordResetTokenService {
    private final PasswordResetTokenRepository passwordResetTokenRepository;
    private final AppUserRepository appUserRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder; // Inject BCryptPasswordEncoder
    private static final Logger logger = LoggerFactory.getLogger(PasswordResetTokenService.class);


    public String createPasswordResetToken(String email) {
        AppUser user = appUserRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalStateException("User not found"));

        String token = generateToken();
        LocalDateTime expiresAt = LocalDateTime.now().plusMinutes(15);

        PasswordResetToken resetToken = new PasswordResetToken(token, user, expiresAt);
        passwordResetTokenRepository.save(resetToken);

        return token;
    }

    private String generateToken() {
        SecureRandom random = new SecureRandom();
        byte[] bytes = new byte[24];
        random.nextBytes(bytes);
        return Base64.getEncoder().encodeToString(bytes);
    }

    public Optional<PasswordResetToken> getToken(String token) {
        return passwordResetTokenRepository.findByToken(token);
    }

    public boolean isTokenExpired(String token) {
        Optional<PasswordResetToken> resetTokenOptional = passwordResetTokenRepository.findByToken(token);

        if (resetTokenOptional.isEmpty()) {
            logger.info("Token not found: {}", token);
            return true; // Treat as expired if not found
        }
        PasswordResetToken resetToken = resetTokenOptional.get();
        logger.info("Token expiry date: {}, Current time: {}", resetToken.getExpiresAt(), LocalDateTime.now());
        return resetToken.getExpiresAt().isBefore(LocalDateTime.now());
    }

    public void resetPassword(String token, String newPassword) {
        Optional<PasswordResetToken> resetTokenOptional = passwordResetTokenRepository.findByToken(token);
        if (resetTokenOptional.isEmpty()) {
            throw new IllegalStateException("Token not found.");
        }
        PasswordResetToken resetToken = resetTokenOptional.get();

        if (isTokenExpired(token)) {
            throw new IllegalStateException("Token has expired");
        }

        AppUser user = resetToken.getAppUser();
        String encodedPassword = bCryptPasswordEncoder.encode(newPassword); // Hash the password
        user.setPassword(encodedPassword);
        appUserRepository.save(user); // Save the updated user
        logger.info("Password reset for user: {}", user.getEmail());

        passwordResetTokenRepository.delete(resetToken); // Delete the token after use (Important!)
    }
}