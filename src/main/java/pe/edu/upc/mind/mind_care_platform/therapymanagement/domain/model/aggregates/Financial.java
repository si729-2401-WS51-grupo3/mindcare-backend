package pe.edu.upc.mind.mind_care_platform.therapymanagement.domain.model.aggregates;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.AbstractAggregateRoot;
import pe.edu.upc.mind.mind_care_platform.therapymanagement.domain.model.commands.CreateFinancialTransactionCommand;
import pe.edu.upc.mind.mind_care_platform.therapymanagement.domain.model.entities.Transaction;
import pe.edu.upc.mind.mind_care_platform.therapymanagement.domain.model.valueobjects.PatientId;
import pe.edu.upc.mind.mind_care_platform.therapymanagement.domain.model.valueobjects.PyschologistId;
import pe.edu.upc.mind.mind_care_platform.therapymanagement.domain.model.valueobjects.ReservationId;
import pe.edu.upc.mind.mind_care_platform.therapymanagement.domain.model.valueobjects.Status;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "financials")
public class Financial extends AbstractAggregateRoot<Financial> {
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Getter
    @OneToMany(mappedBy = "financial", cascade = CascadeType.ALL)
    private List<Transaction> transactions;

    @Embedded
    private PatientId patientId;
    @Embedded
    private PyschologistId pyschologistId;
    /**
     * The status of the financial transaction.
     */

    private Status status;

    public Financial() {
    }

    public Financial(PatientId patientId, PyschologistId pyschologistId) {
        this.patientId = patientId;
        this.pyschologistId = pyschologistId;
        this.status = Status.PENDIENTE;
    }

    public Financial(CreateFinancialTransactionCommand command) {
        this.patientId = new PatientId(command.patientId());
        this.pyschologistId = new PyschologistId(command.psychologistId());
        this.transactions = new ArrayList<>();
        this.status = Status.PENDIENTE;
    }


    /**
     * Cancel the financial transaction.
     */
    public void cancel() {
        this.status = Status.CANCELADO;
    }
    public boolean isCanceled() {
        return this.status == Status.CANCELADO;
    }

    /**
     * Pay the financial transaction.
     */
    public void pay() {
        this.status = Status.PAGADO;
    }
    public boolean isPaid() {
        return this.status == Status.PAGADO;
    }

    /**
     * Returns the status of the financial transaction.
     * @return The status of the financial transaction.
     */
    public String getStatus() {
        return this.status.name().toLowerCase();
    }

    public String getPatientId() {
        return this.patientId.getId();
    }
    public String getPyschologistId() {
        return this.pyschologistId.getId();
    }

}
