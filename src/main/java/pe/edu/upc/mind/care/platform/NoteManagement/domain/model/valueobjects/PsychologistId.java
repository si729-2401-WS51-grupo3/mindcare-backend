package pe.edu.upc.mind.care.platform.NoteManagement.domain.model.valueobjects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public record PsychologistId(@Column(name = "psychologist_id")Long id) {
    public PsychologistId {
        if (id < 0) {
            throw new IllegalArgumentException("Psychologist ID cannot be negative");
        }
    }

    public PsychologistId() {
        this(0L);
    }
}
