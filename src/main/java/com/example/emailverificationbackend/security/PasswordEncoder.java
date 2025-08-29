package com.example.emailverificationbackend.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class PasswordEncoder {

   @Bean
public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder(); // Use 8 instead of 10 or higher
}

}
