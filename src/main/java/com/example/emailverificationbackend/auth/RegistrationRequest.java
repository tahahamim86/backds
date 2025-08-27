package com.example.emailverificationbackend.auth;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import com.example.emailverificationbackend.appuser.AppUserRole;
@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class RegistrationRequest {

    private final String First_name;
    private final String Last_name;
    private final String email;
    private final String password;
    private final AppUserRole app_user_role;
}
