package pe.edu.upc.mind.care.platform.NoteManagement.domain.model.valueobjects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;


@Embeddable
public record PatientId(@Column(name = "patient_id")Long id) {
    public PatientId {
        if (id < 0) {
            throw new IllegalArgumentException("Patient ID cannot be negative");
        }
    }

    public PatientId() {
        this(0L);
    }
}
