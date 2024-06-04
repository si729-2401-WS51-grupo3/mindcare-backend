package pe.edu.upc.mind.mind_care_platform.therapymanagement.interfaces.rest.transform;


import pe.edu.upc.mind.mind_care_platform.therapymanagement.domain.model.commands.CreateFinancialTransactionCommand;
import pe.edu.upc.mind.mind_care_platform.therapymanagement.interfaces.rest.resources.CreateFinancialResource;

public class CreateFinancialCommandFromResourceAssembler {
    public static CreateFinancialTransactionCommand toCommandFromResource(CreateFinancialResource resource) {
        return new CreateFinancialTransactionCommand(resource.patientId(), resource.pyschologistId(), resource.reservationId());
    }
}
