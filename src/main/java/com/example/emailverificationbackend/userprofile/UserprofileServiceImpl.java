package com.example.emailverificationbackend.userprofile;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserprofileServiceImpl implements UserprofileService {

    private final UserprofileRepository userprofileRepository;

    @Override
    @Transactional
    public Userprofile getUserprofileByEmail(String email) {
        return userprofileRepository.findByUserEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("Profile not found for user with email: " + email));
    }

    @Override
    public Userprofile createUserprofile(Userprofile userprofile) {
        if (userprofile == null) {
            throw new IllegalArgumentException("Userprofile cannot be null");
        }
        return userprofileRepository.save(userprofile);
    }

    @Override
    @Transactional
    public Userprofile updateUserprofile(String email, Userprofile updatedUserprofile) {
        if (updatedUserprofile == null) {
            throw new IllegalArgumentException("Userprofile cannot be null");
        }

        Userprofile existingUserprofile = userprofileRepository.findByUserEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("User profile not found for user with email: " + email));

        boolean isUpdated = updateExistingUserprofile(existingUserprofile, updatedUserprofile);

        return isUpdated ? userprofileRepository.save(existingUserprofile) : existingUserprofile;
    }

    @Override
    @Transactional
    public void deleteUserprofile(String email) {
        Userprofile existingUserprofile = userprofileRepository.findByUserEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("User profile not found for user with email: " + email));
        userprofileRepository.delete(existingUserprofile);
    }

    @Override
    public boolean isUserProfileExist(String email) {
        return userprofileRepository.existsByUserEmail(email);
    }

    private boolean updateExistingUserprofile(Userprofile existing, Userprofile updated) {
        boolean isUpdated = false;

        if (updated.getFullName() != null && !updated.getFullName().equals(existing.getFullName())) {
            existing.setFullName(updated.getFullName()); isUpdated = true;
        }
        if (updated.getBirthday() != null && !updated.getBirthday().equals(existing.getBirthday())) {
            existing.setBirthday(updated.getBirthday()); isUpdated = true;
        }
        if (updated.getPlaceBirth() != null && !updated.getPlaceBirth().equals(existing.getPlaceBirth())) {
            existing.setPlaceBirth(updated.getPlaceBirth()); isUpdated = true;
        }
        if (updated.getGender() != null && !updated.getGender().equals(existing.getGender())) {
            existing.setGender(updated.getGender()); isUpdated = true;
        }
        if (updated.getIdentityMatricule() != null && !updated.getIdentityMatricule().equals(existing.getIdentityMatricule())) {
            existing.setIdentityMatricule(updated.getIdentityMatricule()); isUpdated = true;
        }
        if (updated.getCodeNam() != null && !updated.getCodeNam().equals(existing.getCodeNam())) {
            existing.setCodeNam(updated.getCodeNam()); isUpdated = true;
        }
        if (updated.getContacttype() != null && !updated.getContacttype().equals(existing.getContacttype())) {
            existing.setContacttype(updated.getContacttype()); isUpdated = true;
        }
        if (updated.getContactInfo() != null && !updated.getContactInfo().equals(existing.getContactInfo())) {
            existing.setContactInfo(updated.getContactInfo()); isUpdated = true;
        }
        if (updated.getMartialstatus() != null && !updated.getMartialstatus().equals(existing.getMartialstatus())) {
            existing.setMartialstatus(updated.getMartialstatus()); isUpdated = true;
        }
        if (updated.getDescription_martialstatus() != null && !updated.getDescription_martialstatus().equals(existing.getDescription_martialstatus())) {
            existing.setDescription_martialstatus(updated.getDescription_martialstatus()); isUpdated = true;
        }
        if (updated.getNationality() != null && !updated.getNationality().equals(existing.getNationality())) {
            existing.setNationality(updated.getNationality()); isUpdated = true;
        }
        if (updated.getImage() != null && !updated.getImage().equals(existing.getImage())) {
            existing.setImage(updated.getImage()); isUpdated = true;
        }

        return isUpdated;
    }
}
