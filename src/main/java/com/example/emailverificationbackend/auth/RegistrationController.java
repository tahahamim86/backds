package com.example.emailverificationbackend.auth;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Base64;
import com.example.emailverificationbackend.auth.token.PasswordResetTokenService;
import com.example.emailverificationbackend.email.EmailSender;

@RestController
@RequestMapping(path = "api/v1/registration")
@AllArgsConstructor
@CrossOrigin("*")
public class RegistrationController {
    private final RegistrationService registrationService;
    private final PasswordResetTokenService passwordResetTokenService;
    private final EmailSender emailSender;
    private static final Logger logger = LoggerFactory.getLogger(RegistrationController.class);

    @PostMapping
    public String register(@RequestBody RegistrationRequest registrationRequest) {
        return registrationService.register(registrationRequest);
    }

    @GetMapping(path = "confirm")
    public String confirm(@RequestParam("token") String token) {
        return registrationService.confirmToken(token);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(registrationService.login(request));
    }

    @PostMapping("/password-reset")
    public String requestPasswordReset(@RequestBody PasswordResetRequest passwordResetRequest) {
        try {
            String token = passwordResetTokenService.createPasswordResetToken(passwordResetRequest.getEmail());
            logger.info("Generated token: {}", token);
    
            String encodedToken = Base64.getEncoder().encodeToString(token.getBytes());
            String link = "http://localhost:9300/api/v1/registration/reset-password?token=" + encodedToken;
    
            logger.info("Password reset link (before sending): {}", link);
    
            emailSender.send(passwordResetRequest.getEmail(), buildPasswordResetEmail(link));
            logger.info("Email sent successfully to: {}", passwordResetRequest.getEmail());
    
            return "Password reset link has been sent to your email.";
    
        } catch (Exception e) {
            logger.error("Error in requestPasswordReset:", e);
            return "Error sending password reset email. Please try again later.";
        }
    }
    
    @GetMapping("/reset-password")
    public String showResetPasswordForm(@RequestParam("token") String token) {
        logger.info("showResetPasswordForm called with token: {}", token);
        return generatePasswordResetForm(token);
    }
    
    @PostMapping("/reset-password")
    public String resetPassword(@RequestParam("token") String encodedToken, @RequestParam("newPassword") String newPassword) {
        try {
            byte[] decodedToken = Base64.getDecoder().decode(encodedToken);
            String token = new String(decodedToken);
    
            logger.info("resetPassword called with token: {}", token);
    
            if (passwordResetTokenService.isTokenExpired(token)) {
                throw new IllegalStateException("Token has expired or is invalid.");
            }
    
            passwordResetTokenService.resetPassword(token, newPassword);
            return "Password has been successfully reset.";
    
        } catch (IllegalArgumentException e) {
            logger.error("Base64 decoding error:", e);
            return "Invalid password reset token.";
        } catch (IllegalStateException e) {
            logger.error("Token error:", e);
            return "Token has expired or is invalid.";
        } catch (Exception e) {
            logger.error("Error resetting password:", e);
            return "Error resetting password. Please try again later.";
        }
    }
    
    // Helper method to generate the reset password form HTML directly
    private String generatePasswordResetForm(String token) {
        return "<!DOCTYPE html>\n" +
               "<html lang=\"en\">\n" +
               "<head>\n" +
               "    <meta charset=\"UTF-8\">\n" +
               "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
               "    <title>Reset Password</title>\n" +
               "</head>\n" +
               "<body>\n" +
               "    <h2>Reset Your Password</h2>\n" +
               "    <form id=\"resetPasswordForm\" method=\"POST\" action=\"/api/v1/registration/reset-password\">\n" +
               "        <input type=\"hidden\" name=\"token\" value=\"" + token + "\">\n" +
               "        <label for=\"newPassword\">New Password:</label>\n" +
               "        <input type=\"password\" id=\"newPassword\" name=\"newPassword\" required>\n" +
               "        <button type=\"submit\">Reset Password</button>\n" +
               "    </form>\n" +
               "</body>\n" +
               "</html>";
    }

    // Helper method to build the reset password email
    private String buildPasswordResetEmail(String link) {
        return "<div style=\"font-family:Helvetica,Arial,sans-serif;font-size:16px;margin:0;color:#0b0c0c\">\n" +
               "<span style=\"display:none;font-size:1px;color:#fff;max-height:0\"></span>\n" +
               "<table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;min-width:100%;width:100%!important\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\n" +
               "<tbody><tr>\n" +
               "<td width=\"100%\" height=\"53\" bgcolor=\"#0b0c0c\">\n" +
               "    <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;max-width:580px\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" align=\"center\">\n" +
               "        <tbody><tr>\n" +
               "            <td width=\"70\" bgcolor=\"#0b0c0c\" valign=\"middle\">\n" +
               "                <table role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
               "                    <tbody><tr>\n" +
               "                        <td style=\"padding-left:10px\">\n" +
               "                        </td>\n" +
               "                        <td style=\"font-size:28px;line-height:1.315789474;Margin-top:4px;padding-left:10px\">\n" +
               "                            <span style=\"font-family:Helvetica,Arial,sans-serif;font-weight:700;color:#ffffff;text-decoration:none;vertical-align:top;display:inline-block\">Reset Your Password</span>\n" +
               "                        </td>\n" +
               "                    </tr>\n" +
               "                </tbody></table>\n" +
               "            </td>\n" +
               "        </tr>\n" +
               "    </tbody></table>\n" +
               "</td>\n" +
               "</tr>\n" +
               "</tbody></table>\n" +
               "<table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n" +
               "<tbody><tr>\n" +
               "<td height=\"30\"><br></td>\n" +
               "</tr>\n" +
               "<tr>\n" +
               "<td width=\"10\" valign=\"middle\"><br></td>\n" +
               "<td style=\"font-family:Helvetica,Arial,sans-serif;font-size:19px;line-height:1.315789474;max-width:560px\">\n" +
               "<div style=\"border: 1px solid #8BBF9C; border-radius: 10px; padding: 20px; background-color: #0E2517; color: white; text-align: center;\">\n" +
               "    <h2 style=\"font-size: 36px; color: #8BBF9C; font-weight: bold; margin-bottom: 15px;\">Doctor Sina</h2>\n" +
               "    <h2 style=\"font-size: 24px; color: #8BBF9C;\">Reset Your Password</h2>\n" +
               "    <p style=\"font-size: 18px; color: #ffffff;\">Please click the link below to reset your password:</p>\n" +
               "    <blockquote style=\"Margin:0 0 20px 0;border-left:10px solid #b1b4b6;padding:15px 0 0.1px 15px;font-size:19px;line-height:25px\">\n" +
               "        <p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\">\n" +
               "            <a href=\"" + link + "\" style=\"color:#8BBF9C; text-decoration: none;\">Reset Now</a>\n" +
               "        </p>\n" +
               "    </blockquote>\n" +
               "    <p style=\"font-size: 18px; color: #ffffff;\">Link will expire in 15 minutes.</p>\n" +
               "    <p style=\"font-size: 18px; color: #ffffff;\">See you soon!</p>\n" +
               "</div>\n" +
               "</td>\n" +
               "<td width=\"10\" valign=\"middle\"><br></td>\n" +
               "</tr>\n" +
               "<tr>\n" +
               "<td height=\"30\"><br></td>\n" +
               "</tr>\n" +
               "</tbody></table>\n" +
               "<div class=\"yj6qo\"></div><div class=\"adL\">\n" +
               "</div></div>";
    }
}
