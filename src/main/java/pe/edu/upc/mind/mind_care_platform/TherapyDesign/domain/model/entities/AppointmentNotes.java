package pe.edu.upc.mind.mind_care_platform.TherapyDesign.domain.model.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class AppointmentNotes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String patientNotes;
    private int appointmentId;

    // Getters and Setters

}
