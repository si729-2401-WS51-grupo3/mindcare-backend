package pe.edu.upc.mind.care.platform.therapymanagement.domain.model.events;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public final class FinancialTransactionCompletedEvent extends ApplicationEvent {
    private final Long transactionId;

    public FinancialTransactionCompletedEvent(Object source, Long transactionId) {
        super(source);
        this.transactionId = transactionId;
    }
}
