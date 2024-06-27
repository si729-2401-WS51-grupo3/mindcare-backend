package pe.edu.upc.mind.care.platform.therapymanagement.domain.model.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import pe.edu.upc.mind.care.platform.searchandmatch.domain.model.valueobjects.PatientId;
import pe.edu.upc.mind.care.platform.shared.domain.model.aggregate.AuditableAbstractAggregateRoot;
import pe.edu.upc.mind.care.platform.therapymanagement.domain.model.aggregates.Financial;
import pe.edu.upc.mind.care.platform.therapymanagement.domain.model.valueobjects.PyschologistId;
import pe.edu.upc.mind.care.platform.therapymanagement.domain.model.valueobjects.ReservationId;

@Setter
@Getter
@Entity
@Table(name = "transaction")
public class Transaction extends AuditableAbstractAggregateRoot<Transaction> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //id de la transaction

    /**
     * Atributos que tambien tiene la
     * tabla de financials en el PostGress
     */
    @Setter
    @Getter
    @Embedded
    @JsonUnwrapped
    private PatientId patientId; //se buscara solo por patient_id

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "financial_id")
    @JsonBackReference
    private Financial financial; //ESTE ES MI AGGREGATE CONECTADO AL ENTITIE!!

    /**
     * Atributos que tiene solo la tabla de Transaction
     * es decir, no se imprimira direc en la tabla financial
     */
    @Setter
    @Getter
    @Embedded
    @JsonUnwrapped
    private PyschologistId pyschologistId;

    @Setter
    @Getter
    @Embedded
    @JsonUnwrapped
    private ReservationId reservationId;

    private int amount;

   public Transaction(PatientId patientId,Financial financialId, PyschologistId pyschologistId,
                   ReservationId reservationId, int amount) {
    this.patientId = patientId;
    this.financial = financialId;
    this.pyschologistId = pyschologistId;
    this.reservationId = reservationId;
    this.amount = amount;
}

    public Transaction() {

    }

    public Long getId() {
        return id;
    }

}