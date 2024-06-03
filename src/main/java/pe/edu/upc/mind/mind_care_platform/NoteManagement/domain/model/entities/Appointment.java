package pe.edu.upc.mind.mind_care_platform.NoteManagement.domain.model.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import pe.edu.upc.mind.mind_care_platform.NoteManagement.domain.model.aggregates.AppointmentNote;

@Getter
@Entity
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    private String details;

    @OneToOne(mappedBy = "appointment", cascade = CascadeType.ALL)
    private AppointmentNote appointmentNote;

    public Appointment() {
        this.details = "";
    }

    public Appointment(Long id) {
        this.id = id;
        this.details = "";
    }

    public Appointment(Long id, String details) {
        this.id = id;
        this.details = details;
    }
}
