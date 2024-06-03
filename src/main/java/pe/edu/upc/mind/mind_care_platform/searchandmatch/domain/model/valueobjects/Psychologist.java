package pe.edu.upc.mind.mind_care_platform.searchandmatch.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record Psychologist (Long profileId, String speciality){
    public Psychologist {
        if (profileId < 0) {
            throw new IllegalArgumentException("PsychologistId cannot be negative");
        }
        if (speciality.isBlank()) {
            throw new IllegalArgumentException("Speciality cannot be empty");
        }
    }
    public Psychologist() {
        this(0L, "");
    }
}