package pe.edu.upc.mind.mind_care_platform.searchandmatch.domain.model.entities;
import pe.edu.upc.mind.mind_care_platform.searchandmatch.domain.model.entities.Appointment;
import jakarta.persistence.*;
import pe.edu.upc.mind.mind_care_platform.shared.domain.model.entities.User;

import java.util.List;

@Entity
public class Patient extends User {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int id;


        @OneToMany(mappedBy = "patient")
        private List<Appointment> appointments;
}
