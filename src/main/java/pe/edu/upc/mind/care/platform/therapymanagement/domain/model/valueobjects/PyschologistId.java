package pe.edu.upc.mind.care.platform.therapymanagement.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

/**
 * Value object representing the pyschologist id.
 */
@Embeddable
public record PyschologistId(Long pyschologistId) {
    public PyschologistId {
        if (pyschologistId == null) {
            throw new IllegalArgumentException("PyschologistId cannot be null");
        }
    }
    public Long value() {
        return pyschologistId;
    }

}