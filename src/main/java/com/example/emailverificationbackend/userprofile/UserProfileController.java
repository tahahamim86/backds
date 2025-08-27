package com.example.emailverificationbackend.userprofile;

import com.example.emailverificationbackend.appuser.AppUser;
import com.example.emailverificationbackend.appuser.AppUserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.example.emailverificationbackend.auth.token.JwtService;

import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/profile")
@RequiredArgsConstructor
public class UserProfileController {

    private final UserprofileService userprofileService;
    private final AppUserRepository appUserRepository;
    private final JwtService jwtService;

    // Get profile by Email
    @GetMapping("/email/{email}")
    public ResponseEntity<?> getProfileByEmail(@PathVariable String email) {
        Optional<AppUser> appUserOptional = appUserRepository.findByEmail(email);
        if (appUserOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
        }

        AppUser appUser = appUserOptional.get();

        try {
            Userprofile userprofile = userprofileService.getUserprofileByEmail(appUser.getEmail());
            Map<String, Object> response = buildProfileResponse(userprofile);
            return ResponseEntity.ok(response);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Profile not found.");
        }
    }

    // Create a new profile
    @PostMapping("/add")
    public ResponseEntity<?> addProfile(
            @RequestParam("fullName") String fullName,
            @RequestParam("birthday") String birthday,
            @RequestParam("placeBirth") String placeBirth,
            @RequestParam("gender") String gender,
            @RequestParam("identityMatricule") String identityMatricule,
            @RequestParam("codeNam") String codeNam,
            @RequestParam("contacttype") String contacttype,
            @RequestParam("contactInfo") String contactInfo,
            @RequestParam("martialstatus") String martialstatus,
            @RequestParam("description_martialstatus") String descriptionMartialstatus,
            @RequestParam("nationality") String nationality,
            @RequestParam(value = "image", required = false) MultipartFile image,
            @RequestParam("email") String email
    ) {
        try {
            Optional<AppUser> appUserOptional = appUserRepository.findByEmail(email);
            if (appUserOptional.isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User not found.");
            }

            AppUser appUser = appUserOptional.get();

            if (userprofileService.isUserProfileExist(appUser.getEmail())) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Profile already exists for this user.");
            }

            Userprofile profile = new Userprofile();
            profile.setFullName(fullName);
            profile.setBirthday(birthday);
            profile.setPlaceBirth(placeBirth);
            profile.setGender(gender);
            profile.setIdentityMatricule(identityMatricule);
            profile.setCodeNam(codeNam);
            profile.setContacttype(contacttype);
            profile.setContactInfo(contactInfo);
            profile.setMartialstatus(martialstatus);
            profile.setDescription_martialstatus(descriptionMartialstatus);
            profile.setNationality(nationality);
            if (image != null && !image.isEmpty()) {
                profile.setImage(image.getBytes());
            }
            profile.setUser(appUser);

            Userprofile savedProfile = userprofileService.createUserprofile(profile);
            Map<String, Object> response = buildProfileResponse(savedProfile);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);

        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error processing image: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }

    // Update an existing profile by Email
    @PutMapping("/update/{email}")
    public ResponseEntity<?> updateProfile(
            @PathVariable String email,
            @RequestParam("fullName") String fullName,
            @RequestParam("birthday") String birthday,
            @RequestParam("placeBirth") String placeBirth,
            @RequestParam("gender") String gender,
            @RequestParam("identityMatricule") String identityMatricule,
            @RequestParam("codeNam") String codeNam,
            @RequestParam("contacttype") String contacttype,
            @RequestParam("contactInfo") String contactInfo,
            @RequestParam("martialstatus") String martialstatus,
            @RequestParam("description_martialstatus") String descriptionMartialstatus,
            @RequestParam("nationality") String nationality,
            @RequestParam(value = "image", required = false) MultipartFile image
    ) {
        try {
            Optional<AppUser> appUserOptional = appUserRepository.findByEmail(email);
            if (appUserOptional.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
            }

            AppUser appUser = appUserOptional.get();

            Userprofile updatedProfile = new Userprofile();
            updatedProfile.setFullName(fullName);
            updatedProfile.setBirthday(birthday);
            updatedProfile.setPlaceBirth(placeBirth);
            updatedProfile.setGender(gender);
            updatedProfile.setIdentityMatricule(identityMatricule);
            updatedProfile.setCodeNam(codeNam);
            updatedProfile.setContacttype(contacttype);
            updatedProfile.setContactInfo(contactInfo);
            updatedProfile.setMartialstatus(martialstatus);
            updatedProfile.setDescription_martialstatus(descriptionMartialstatus);
            updatedProfile.setNationality(nationality);
            if (image != null && !image.isEmpty()) {
                updatedProfile.setImage(image.getBytes());
            }

            Userprofile savedProfile = userprofileService.updateUserprofile(appUser.getEmail(), updatedProfile);
            Map<String, Object> response = buildProfileResponse(savedProfile);
            return ResponseEntity.ok(response);

        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error processing image: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }

    // Delete a profile by Email
    @DeleteMapping("/delete/{email}")
    public ResponseEntity<?> deleteProfile(@PathVariable String email) {
        Optional<AppUser> appUserOptional = appUserRepository.findByEmail(email);
        if (appUserOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
        }

        AppUser appUser = appUserOptional.get();

        try {
            userprofileService.deleteUserprofile(appUser.getEmail());
            return ResponseEntity.ok("Profile for email " + email + " has been deleted.");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Profile not found for deletion.");
        }
    }

    // Helper method to build clean JSON response
    private Map<String, Object> buildProfileResponse(Userprofile profile) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", profile.getId());
        map.put("fullName", profile.getFullName());
        map.put("birthday", profile.getBirthday());
        map.put("placeBirth", profile.getPlaceBirth());
        map.put("gender", profile.getGender());
        map.put("identityMatricule", profile.getIdentityMatricule());
        map.put("codeNam", profile.getCodeNam());
        map.put("contacttype", profile.getContacttype());
        map.put("contactInfo", profile.getContactInfo());
        map.put("martialstatus", profile.getMartialstatus());
        map.put("description_martialstatus", profile.getDescription_martialstatus());
        map.put("nationality", profile.getNationality());
        if (profile.getImage() != null) {
            map.put("image", Base64.getEncoder().encodeToString(profile.getImage()));
        }
        return map;
    }
}
