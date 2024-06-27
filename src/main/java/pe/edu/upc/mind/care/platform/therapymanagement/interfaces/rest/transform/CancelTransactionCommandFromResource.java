package pe.edu.upc.mind.care.platform.therapymanagement.interfaces.rest.transform;

import org.springframework.stereotype.Component;
import pe.edu.upc.mind.care.platform.therapymanagement.domain.model.commands.CancelTransactionCommand;
import pe.edu.upc.mind.care.platform.therapymanagement.interfaces.rest.resources.CancelTransactionCommandResource;

@Component
public class CancelTransactionCommandFromResource {
    public CancelTransactionCommand toCommandFromResource(CancelTransactionCommandResource resource) {
        return new CancelTransactionCommand(
                resource.patientId(),
                resource.transactionId()
        );
    }
}