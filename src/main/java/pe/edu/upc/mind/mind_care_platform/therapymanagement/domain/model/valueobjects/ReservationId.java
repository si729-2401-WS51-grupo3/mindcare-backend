package pe.edu.upc.mind.mind_care_platform.therapymanagement.domain.model.valueobjects;

import jakarta.persistence.*;

/**
 * Value object representing the meeting id.
 */
@Embeddable
//bounded context de search and match
public record ReservationId(Long reservationId) {
    public ReservationId {
        if (reservationId == null) {
            throw new IllegalArgumentException("ReservationId cannot be null");
        }
    }

    public ReservationId(){
        this(null);
    }
}
