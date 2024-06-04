package pe.edu.upc.mind.mind_care_platform.therapymanagement.domain.services;

import pe.edu.upc.mind.mind_care_platform.therapymanagement.domain.model.aggregates.Financial;
import pe.edu.upc.mind.mind_care_platform.therapymanagement.domain.model.commands.CancelFinancialTransactionCommand;
import pe.edu.upc.mind.mind_care_platform.therapymanagement.domain.model.commands.CreateFinancialTransactionCommand;
import pe.edu.upc.mind.mind_care_platform.therapymanagement.domain.model.commands.PayFinancialTransactionCommand;
import pe.edu.upc.mind.mind_care_platform.therapymanagement.domain.model.commands.UpdatedFinancialTransactionCommand;

import java.util.Optional;

public interface FinancialCommandService {
    Long handle(CreateFinancialTransactionCommand command);
    Optional<Financial>handle(UpdatedFinancialTransactionCommand command);

    //Estas funciones administran el estado de la transacción financiera
    Long handle(CancelFinancialTransactionCommand command);
    Long handle(PayFinancialTransactionCommand command);
}
