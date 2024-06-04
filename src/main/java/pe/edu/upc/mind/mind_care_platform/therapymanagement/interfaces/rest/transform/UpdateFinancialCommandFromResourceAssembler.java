package pe.edu.upc.mind.mind_care_platform.therapymanagement.interfaces.rest.transform;

import pe.edu.upc.mind.mind_care_platform.therapymanagement.domain.model.commands.UpdatedFinancialTransactionCommand;
import pe.edu.upc.mind.mind_care_platform.therapymanagement.interfaces.rest.resources.UpdateFinancialResource;

public class UpdateFinancialCommandFromResourceAssembler {
    public static UpdatedFinancialTransactionCommand toCommandFromResource(UpdateFinancialResource resource) {
        return new UpdatedFinancialTransactionCommand(resource.transactionId(), resource.amount());
    }
}
