package pe.edu.upc.mind.mind_care_platform.therapymanagement.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

import java.util.UUID;

/**
 * Value object representing the patient id.
 */
@Embeddable
public record PatientId(String patientId) {
    public PatientId {
        if (patientId == null) {
            throw new IllegalArgumentException("PatientId cannot be null");
        }
    }
}
