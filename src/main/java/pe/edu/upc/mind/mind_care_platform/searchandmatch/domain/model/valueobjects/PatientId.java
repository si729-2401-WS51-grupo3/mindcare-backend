package pe.edu.upc.mind.mind_care_platform.searchandmatch.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record  PatientId (Long patientId){

    public PatientId {
        if (patientId < 0) {
            throw new IllegalArgumentException("PatientId cannot be negative");
        }
    }
}