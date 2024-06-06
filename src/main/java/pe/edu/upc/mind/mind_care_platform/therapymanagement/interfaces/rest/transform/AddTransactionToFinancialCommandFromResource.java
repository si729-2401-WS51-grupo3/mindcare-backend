package pe.edu.upc.mind.mind_care_platform.therapymanagement.interfaces.rest.transform;

import org.springframework.stereotype.Component;
import pe.edu.upc.mind.mind_care_platform.therapymanagement.domain.model.commands.AddTransactionToFinancialCommand;
import pe.edu.upc.mind.mind_care_platform.therapymanagement.interfaces.rest.resources.AddTransactionToFinancialResource;

@Component
public class AddTransactionToFinancialCommandFromResource {
    public AddTransactionToFinancialCommand toCommand(AddTransactionToFinancialResource resource) {
        return new AddTransactionToFinancialCommand(
            resource.getPatientId(),
            resource.getPyschologistId(),
            resource.getReservationId(),
            resource.getAmount()
        );
    }
}