package pe.edu.upc.mind.mind_care_platform.NoteManagement.domain.model.valueobjects;

import jakarta.persistence.Embeddable;


@Embeddable
public record PatientId(Long id) {
    public PatientId {
        if (id < 0) {
            throw new IllegalArgumentException("Patient ID cannot be negative");
        }
    }

    public PatientId() {
        this(0L);
    }
}
