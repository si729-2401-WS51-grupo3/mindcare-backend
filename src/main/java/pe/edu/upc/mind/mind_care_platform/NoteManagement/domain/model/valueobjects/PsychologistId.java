package pe.edu.upc.mind.mind_care_platform.NoteManagement.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record PsychologistId(Long id) {
    public PsychologistId {
        if (id < 0) {
            throw new IllegalArgumentException("Psychologist ID cannot be negative");
        }
    }

    public PsychologistId() {
        this(0L);
    }
}
