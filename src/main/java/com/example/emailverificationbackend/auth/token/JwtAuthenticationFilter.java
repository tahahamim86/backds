package com.example.emailverificationbackend.auth.token;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.filter.OncePerRequestFilter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Collections;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;

    public JwtAuthenticationFilter(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        
        String token = request.getHeader("Authorization");

        // Check if the Authorization header contains a Bearer token
        if (token != null && token.startsWith("Bearer ")) {
            String jwt = token.substring(7); // Remove the "Bearer " prefix

            try {
                if (jwtService.isTokenValid(jwt)) {
                    String username = jwtService.extractemail(jwt); // Extract the username (email in this case)

                    // Assuming roles are fetched from the JWT or somewhere else. 
                    // For example, here I am just using "USER" as a default role.
                    // You can modify this to extract roles from JWT claims if available.
                    SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_USER");  // Customize the role as per your application

                    // Authenticate the user with the extracted username and roles
                    UsernamePasswordAuthenticationToken authentication = 
                        new UsernamePasswordAuthenticationToken(username, null, Collections.singletonList(authority));
                    SecurityContextHolder.getContext().setAuthentication(authentication);

                    // Log the authentication for debugging purposes
                    System.out.println("Authenticated user: " + username);
                }
            } catch (Exception e) {
                // If token is invalid, log the error
                System.out.println("Invalid JWT token: " + e.getMessage());
            }
        } else {
            // No token found or incorrect format
            System.out.println("No JWT token or incorrect Authorization header format.");
        }

        // Continue the filter chain
        chain.doFilter(request, response);
    }
}
