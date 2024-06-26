package pe.edu.upc.mind.care.platform.therapymanagement.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

/**
 * Value object representing the reservation id.
 */
@Embeddable
public record ReservationId(Long reservationId) {
    public ReservationId {
        if (reservationId == null) {
            throw new IllegalArgumentException("ReservationId cannot be null");
        }
    }
    public Long value(){
        return reservationId;
    }

}