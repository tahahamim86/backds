package com.example.emailverificationbackend.appuser;

import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.emailverificationbackend.MedicalRecord.MedicalRecord;
import com.example.emailverificationbackend.MedicalRecord.MedicalRecordRepository;
import com.example.emailverificationbackend.auth.token.ConfirmationToken;
import com.example.emailverificationbackend.auth.token.ConfirmationTokenService;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AppUserService implements UserDetailsService {

    private final static String USER_NOT_FOUND_MSG =
            "user with email %s not found";
    private final AppUserRepository appUserRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ConfirmationTokenService confirmationTokenService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return appUserRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG , email)));
    }

    public String signUpUser(AppUser appUser) {
        boolean userExists = appUserRepository.findByEmail(appUser.getEmail()).isPresent();
        if(userExists) {
            throw new IllegalStateException("Email Already exists or Taken.");
        }
        String encodedPassword = bCryptPasswordEncoder.encode(appUser.getPassword());
        appUser.setPassword(encodedPassword);
        appUserRepository.save(appUser);

        String token = UUID.randomUUID().toString();
        ConfirmationToken confirmationToken = new ConfirmationToken(token , LocalDateTime.now() , LocalDateTime.now().plusMinutes(15) , appUser);
        confirmationTokenService.saveConfirmationToken(confirmationToken);
        return token;
    }
    public int enableAppUser(String email) {
        return appUserRepository.enableAppUser(email);


    }
    @Autowired
    private MedicalRecordRepository medicalRecordRepository;  // Inject the repository for MedicalRecord

    public AppUser createNewAppUser(String firstName, String lastName, String email, String password, AppUserRole role) {
        // Create the AppUser
        AppUser appUser = new AppUser(firstName, lastName, email, password, role);
        appUserRepository.save(appUser);  // Save the AppUser to the database

        // Create a MedicalRecord for the newly created AppUser
        MedicalRecord medicalRecord = new MedicalRecord();
        medicalRecord.setAppUser(appUser);  // Set the relationship with the AppUser
        medicalRecordRepository.save(medicalRecord);  // Save the MedicalRecord to the database

        return appUser;
}

}
