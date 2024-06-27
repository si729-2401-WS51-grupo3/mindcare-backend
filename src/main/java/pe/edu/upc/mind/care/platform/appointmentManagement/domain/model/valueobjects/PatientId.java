package pe.edu.upc.mind.care.platform.appointmentManagement.domain.model.valueobjects;
import jakarta.persistence.Embeddable;

/**
 * Value object representing the patient id.
 */
@Embeddable
public record PatientId(Long patientId) {
    public PatientId {
        if (patientId == null) {
            throw new IllegalArgumentException("PatientId cannot be null");
        }
    }
    public PatientId() {
        this(0L);
    }
}
