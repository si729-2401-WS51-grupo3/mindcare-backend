package pe.edu.upc.mind.mind_care_platform.profiles.domain.model.aggregates;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import org.apache.catalina.User;
import pe.edu.upc.mind.mind_care_platform.profiles.domain.model.entities.Profile;
@Getter
@Entity
public class Psychologist extends Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private Integer scheduleId;

    public Psychologist(String firstName, String lastName, String email, String phone, String photoUrl, String occupation, String description, String gender, String birthDate, Integer scheduleId) {
        super(firstName, lastName, email, phone, photoUrl, occupation, description, gender, birthDate, "PSYCHOLOGIST");
        this.scheduleId = scheduleId;
    }

    public Psychologist() {

    }
    @Override
    public void update(String email, String phone) {
        super.update(email, phone);
    }
}