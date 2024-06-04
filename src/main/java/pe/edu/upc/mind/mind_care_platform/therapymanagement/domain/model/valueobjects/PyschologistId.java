package pe.edu.upc.mind.mind_care_platform.therapymanagement.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

import java.util.UUID;

/**
 * Value object representing the pyschologist id.
 */
@Embeddable
public record PyschologistId(String pyschologistId) {
    public PyschologistId {
        if (pyschologistId == null) {
            throw new IllegalArgumentException("PyschologistId cannot be null");
        }
    }
    public String getId() {
        return this.pyschologistId;
    }
}
