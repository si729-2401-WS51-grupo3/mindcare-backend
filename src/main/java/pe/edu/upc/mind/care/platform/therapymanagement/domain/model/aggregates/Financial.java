package pe.edu.upc.mind.care.platform.therapymanagement.domain.model.aggregates;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.AbstractAggregateRoot;
import pe.edu.upc.mind.care.platform.therapymanagement.domain.model.entities.Transaction;

import java.util.ArrayList;
import java.util.List;
@Setter
@Getter
@Entity

public class Financial extends AbstractAggregateRoot<Financial> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long financial_id;

    private Long patientId;

    @Getter
    @OneToMany(mappedBy = "financial", cascade = CascadeType.ALL,orphanRemoval = true, fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<Transaction> transactions= new ArrayList<>(); ;


    public Financial() {

    }

    /**
     * Basico CRUD
     */
    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
        transaction.setFinancial(this);
    }

    public void removeTransaction(Transaction transaction) {
        transactions.remove(transaction);
        transaction.setFinancial(null);
    }

    public Object getId() {
        return financial_id;
    }

}