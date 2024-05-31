package pe.edu.upc.mind.mind_care_platform.therapymanagement.domain.model.aggregates;

import jakarta.persistence.*;
import org.apache.logging.log4j.util.Strings;
import pe.edu.upc.mind.mind_care_platform.shared.domain.model.entities.AuditableModel;
import pe.edu.upc.mind.mind_care_platform.therapymanagement.domain.model.entities.MeetingDetails;
@Entity
public class Financial extends AuditableModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String amount;

    @OneToOne
    @JoinColumn(name = "meeting_id")
    private MeetingDetails meetingDetails;

    public Financial() {
        this.amount = Strings.EMPTY;
        this.meetingDetails = null;
    }

    public Financial(String amount, MeetingDetails meetingDetails) {
        this();
        this.amount = amount;
        this.meetingDetails = meetingDetails;
    }

    /**
     * Updates the transaction information.
     * @param amount The new amount.
     * @param meetingDetails The new meetingDetails.
     * @return The updated transaction.
     */
    public Financial updateInformation(String amount, MeetingDetails meetingDetails) {
        this.amount = amount;
        this.meetingDetails = meetingDetails;
        return this;
    }
}
