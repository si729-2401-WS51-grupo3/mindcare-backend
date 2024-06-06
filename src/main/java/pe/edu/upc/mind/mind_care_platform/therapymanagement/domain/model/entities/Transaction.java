package pe.edu.upc.mind.mind_care_platform.therapymanagement.domain.model.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import pe.edu.upc.mind.mind_care_platform.shared.domain.model.entities.AuditableModel;
import pe.edu.upc.mind.mind_care_platform.therapymanagement.domain.model.aggregates.Financial;
import pe.edu.upc.mind.mind_care_platform.therapymanagement.domain.model.valueobjects.PatientId;
import pe.edu.upc.mind.mind_care_platform.therapymanagement.domain.model.valueobjects.PyschologistId;
import pe.edu.upc.mind.mind_care_platform.therapymanagement.domain.model.valueobjects.ReservationId;

import java.util.Map;

@Setter
@Getter
@Entity
@Table(name = "transaction")
public class Transaction {
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
    private PyschologistId pyschologistId;

    @Setter
    @Getter
    @Embedded
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

    public Object getId() {
        return id;
    }

}