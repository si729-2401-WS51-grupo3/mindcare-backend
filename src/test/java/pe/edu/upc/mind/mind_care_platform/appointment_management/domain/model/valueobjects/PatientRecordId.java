package pe.edu.upc.mind.mind_care_platform.appointment_management.domain.model.valueobjects;
import jakarta.persistence.Embeddable;
import java.util.UUID;
/**
 * Value object representing the psychologist record id.
 */
@Embeddable
public record PatientRecordId(String patientRecordId) {
    public PatientRecordId() {
        this(UUID.randomUUID().toString());
    }

    public PatientRecordId {
        if (patientRecordId == null || patientRecordId.isBlank()) {
            throw new IllegalArgumentException("patient record profileId cannot be null or blank");
        }
    }
}
