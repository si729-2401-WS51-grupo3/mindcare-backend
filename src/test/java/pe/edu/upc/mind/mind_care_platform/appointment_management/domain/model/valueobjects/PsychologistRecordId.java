package pe.edu.upc.mind.mind_care_platform.appointment_management.domain.model.valueobjects;
import jakarta.persistence.Embeddable;
import java.util.UUID;
/**
 * Value object representing the psychologist record id.
 */
@Embeddable
public record PsychologistRecordId(String psychologistRecordId) {
    public PsychologistRecordId() {
        this(UUID.randomUUID().toString());
    }

    public PsychologistRecordId {
        if (psychologistRecordId == null || psychologistRecordId.isBlank()) {
            throw new IllegalArgumentException("psychologist record profileId cannot be null or blank");
        }
    }
}
