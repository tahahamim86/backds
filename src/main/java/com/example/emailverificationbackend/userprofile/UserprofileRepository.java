package com.example.emailverificationbackend.userprofile;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.emailverificationbackend.appuser.AppUser;

public interface UserprofileRepository extends JpaRepository<Userprofile, Long> {

    boolean existsByUserEmail(String email);

    Optional<Userprofile> findByUserEmail(String email);
}
