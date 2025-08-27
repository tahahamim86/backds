package com.example.emailverificationbackend.userprofile;


import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import com.example.emailverificationbackend.appuser.AppUser;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToOne;
import lombok.Data;
@Entity
@Data
public class Userprofile {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "fullname", length = 255, nullable = false)
    private String fullName;
    @Column(name = "birthday")
    private String birthday;
    @Column(name = "placebirth", length = 255)
    private String placeBirth;
    @Column(name = "gender", length = 255)
    private String gender;
    @Column(name = "identitymatricule", length = 255)
    private String identityMatricule;
    @Column(name = "codenam", length = 255)
    private String codeNam;
    @Column(name = "contacttype",length=255)
    private String contacttype;
    @Column(name = "contactInfo",length=255)
    private String contactInfo;
    @Column(name = "martialstatus",length=255)
    private String martialstatus;
    @Column(name = "martialstatus_description",length=255)
    private String description_martialstatus;
    @Column(name = "nationality",length=255)
    private String nationality;
    @Lob
    @JdbcTypeCode(SqlTypes.BINARY)
    @Column(nullable = true, columnDefinition = "BYTEA")
    private byte[] image;
        @JsonIgnore  // Prevents infinite recursion in the back reference
      @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private AppUser user;



    
}
