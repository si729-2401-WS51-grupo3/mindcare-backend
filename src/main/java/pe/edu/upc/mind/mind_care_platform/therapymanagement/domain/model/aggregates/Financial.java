package pe.edu.upc.mind.mind_care_platform.therapymanagement.domain.model.aggregates;

import jakarta.persistence.*;
import org.apache.logging.log4j.util.Strings;
import pe.edu.upc.mind.mind_care_platform.shared.domain.model.entities.AuditableModel;
import pe.edu.upc.mind.mind_care_platform.therapymanagement.domain.model.entities.Amount;
import pe.edu.upc.mind.mind_care_platform.therapymanagement.domain.model.valueobjects.ReservationId;

@Entity
public class Financial extends AuditableModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @OneToOne(mappedBy = "financial", cascade = CascadeType.ALL)
    private Amount amount;

    private Long reservationId;

    public Financial() {
        this.amount = new Amount(this);
    }

    /**
     * Updates the transaction information.
     * @param newAmount The new amount.
     * @param newReservationId The new meetingDetails.
     * @return The updated transaction.
     */

    public void updateInformation(Amount newAmount, Long newReservationId) {
        this.amount = newAmount;
        this.reservationId = newReservationId;
    }
}
