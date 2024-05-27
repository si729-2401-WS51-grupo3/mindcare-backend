package pe.edu.upc.mind.mind_care_platform.searchandmatch.domain.model.entities;

import jakarta.persistence.*;
import lombok.Getter;
import pe.edu.upc.mind.mind_care_platform.shared.domain.model.entities.User;

import java.util.List;

@Entity
public class Psychologist extends User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToMany(mappedBy = "psychologist")
    private List<Appointment> appointments;

}
