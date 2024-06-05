package pe.edu.upc.mind.mind_care_platform.NoteManagement.domain.model.valueobjects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public record AppointmentId(@Column(name = "appointment_id")Long id) {
    public AppointmentId {
        if (id < 0) {
            throw new IllegalArgumentException("Appointment ID cannot be negative");
        }
    }

    public AppointmentId() {
        this(0L);
    }
}
