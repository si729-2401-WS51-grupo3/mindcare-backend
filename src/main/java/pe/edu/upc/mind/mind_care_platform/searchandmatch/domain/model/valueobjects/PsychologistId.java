package pe.edu.upc.mind.mind_care_platform.searchandmatch.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record PsychologistId (Long profileId){
    public PsychologistId {
        if (profileId < 0) {
            throw new IllegalArgumentException("PsychologistId cannot be negative");
        }
    }
    public PsychologistId() {
        this(0L);
    }
}