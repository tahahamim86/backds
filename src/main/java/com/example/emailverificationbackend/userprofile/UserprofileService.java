package com.example.emailverificationbackend.userprofile;

import com.example.emailverificationbackend.appuser.AppUser;

public interface UserprofileService {

    Userprofile getUserprofileByEmail(String email);

    Userprofile createUserprofile(Userprofile userprofile);

    Userprofile updateUserprofile(String email, Userprofile userprofile);

    void deleteUserprofile(String email);

    boolean isUserProfileExist(String email);
}
