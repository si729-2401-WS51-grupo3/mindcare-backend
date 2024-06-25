package pe.edu.upc.mind.mind_care_platform.profiles.domain.model.aggregates;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import pe.edu.upc.mind.mind_care_platform.profiles.domain.model.entities.Profile;

@Getter
@Entity
public class Patient extends Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    public Patient(String firstName, String lastName, String email, String phone, String photoUrl, String occupation, String description, String gender, String birthDate) {
        super(firstName, lastName, email, phone, photoUrl, occupation, description, gender, birthDate, "PATIENT");
    }

    public Patient() {


    }
    @Override
    public void update(String email, String phone) {
        super.update(email, phone);
    }



}