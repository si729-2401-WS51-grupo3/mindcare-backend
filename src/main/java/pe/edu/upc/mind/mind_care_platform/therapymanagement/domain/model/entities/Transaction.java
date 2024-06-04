package pe.edu.upc.mind.mind_care_platform.therapymanagement.domain.model.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import pe.edu.upc.mind.mind_care_platform.shared.domain.model.entities.AuditableModel;
import pe.edu.upc.mind.mind_care_platform.therapymanagement.domain.model.aggregates.Financial;
import pe.edu.upc.mind.mind_care_platform.therapymanagement.domain.model.valueobjects.ReservationId;
import pe.edu.upc.mind.mind_care_platform.therapymanagement.domain.model.valueobjects.Status;

@Setter
@Getter
@Entity
@Embeddable
public class Transaction extends AuditableModel {
    @Id
    private Long id;
    private Integer amount;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Embedded
    private ReservationId reservationId;

    @ManyToOne
    @JoinColumn(name = "financial_id")
    private Financial financial;

    public Transaction() {
    }

    public void updateAmount(Integer amount) {
        this.amount = amount;
    }

    public Transaction(ReservationId reservationId, Integer amount) {
        this.reservationId = reservationId;
        this.amount = amount;
        this.status = Status.PENDIENTE;
    }

}