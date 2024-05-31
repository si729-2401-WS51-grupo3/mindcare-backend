package pe.edu.upc.mind.mind_care_platform.therapymanagement.domain.model.events;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class FinancialCompletedEvent extends ApplicationEvent {

    private final Long financialId;

    public FinancialCompletedEvent(Object source, Long financialId) {
        super(source);
        this.financialId = financialId;
    }
}
