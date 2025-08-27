package com.example.emailverificationbackend.medicalstructure;

import java.util.List;

import com.example.emailverificationbackend.pays.Pays;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
public class Medicalstructure {
         @Id
         @GeneratedValue(strategy = GenerationType.IDENTITY)
         private Long id;
         @OneToOne(cascade=CascadeType.ALL)
         @JoinColumn(name="structuretype_id",referencedColumnName = "id")
         private Structuretype structuretype;
         @OneToOne(cascade=CascadeType.ALL)
         @JoinColumn(name="pays_id",referencedColumnName = "id")
         private Pays pays;
         @OneToOne
         @JoinColumn(name = "site_id") // This is the foreign key
         private Site site; // Ensure this name matches what's referenced in Site
         private String speciality;
         private String label;
         private String description;
         private String alias;
         private String insurancecode;
         private String logitude;
         private String latitude;
         private String observation;
        @OneToMany(mappedBy = "mediastructure") 
        private List<Structureoffer> structureOffers;
         
}
