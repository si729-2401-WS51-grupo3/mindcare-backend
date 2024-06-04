package pe.edu.upc.mind.mind_care_platform.therapymanagement.domain.model.valueobjects;

import jakarta.persistence.*;

/**
 * Value object representing the reservation id.
 */
@Embeddable
public record ReservationId(String reservationId) {
    public ReservationId {
        if (reservationId == null) {
            throw new IllegalArgumentException("ReservationId cannot be null");
        }
    }
    public ReservationId(){
        this(null);
    }
}
