package pe.edu.upc.mind.care.platform.therapymanagement.interfaces.rest.transform;

import org.springframework.stereotype.Component;
import pe.edu.upc.mind.care.platform.therapymanagement.domain.model.commands.AddTransactionToFinancialCommand;
import pe.edu.upc.mind.care.platform.therapymanagement.interfaces.rest.resources.AddTransactionToFinancialResource;

@Component
public class AddTransactionToFinancialCommandFromResource {
    public AddTransactionToFinancialCommand toCommandFromResource(AddTransactionToFinancialResource resource) {
        if (resource.pyschologistId() == null) {
            throw new IllegalArgumentException("PyschologistId cannot be null");
        }

        return new AddTransactionToFinancialCommand(
                resource.patientId(),
                resource.pyschologistId(),
                resource.reservationId(),
                resource.amount()
        );
    }
}