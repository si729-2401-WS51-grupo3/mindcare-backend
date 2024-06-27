package pe.edu.upc.mind.care.platform.searchandmatch.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record  PsychologistId (Long psychologistId){

    public PsychologistId {
        if (psychologistId < 0) {
            throw new IllegalArgumentException("PsychologistId cannot be negative");
        }
    }
}