package pe.edu.upc.mind.mind_care_platform.therapymanagement.domain.model.valueobjects;

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
    /**
     *  El método getter simplemente te permite acceder al valor del patientId
     *  desde fuera de la clase PatientId, pero no te permite cambiar ese valor
     *  una vez que el objeto PatientId ha sido creado.
     */
    public Long value() {
        return patientId;
    }

    public Long getId() {
        return patientId;
    }
}