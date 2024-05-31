package pe.edu.upc.mind.mind_care_platform.appointmentManagement.domain.model.valueobjects;
import jakarta.persistence.Embeddable;
/**
 * Value object representing the patient id.
 */
@Embeddable
public record PatientId(Long patientId) {
    public PatientId {
        if (patientId < 0) {
            throw new IllegalArgumentException("Patient patientId cannot be negative");
        }
    }

    public PatientId() {
        this(0L);
    }
}
