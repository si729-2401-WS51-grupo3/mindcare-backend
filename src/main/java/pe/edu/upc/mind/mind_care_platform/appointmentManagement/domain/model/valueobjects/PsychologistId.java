package pe.edu.upc.mind.mind_care_platform.appointmentManagement.domain.model.valueobjects;
import jakarta.persistence.Embeddable;

/**
 * Value object representing the psychologist id.
 */
@Embeddable
public record PsychologistId(Long psychologistId) {
    public PsychologistId {
        if (psychologistId < 0) {
            throw new IllegalArgumentException("PsychologistId cannot be null");
        }
    }
    public PsychologistId() {
        this(0L);
    }
}
