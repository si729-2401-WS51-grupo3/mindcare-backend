package pe.edu.upc.mind.mind_care_platform.therapymanagement.domain.model.services;

import pe.edu.upc.mind.mind_care_platform.therapymanagement.domain.model.commands.CancelFinancialTransactionCommand;
import pe.edu.upc.mind.mind_care_platform.therapymanagement.domain.model.commands.CompleteFinancialCommand;

/**
 * cuando se llama al método handle con
 * un comando de tipo CompleteFinancialCommand,
 * este realiza una serie de operaciones
 * para completar la transacción financiera.
 * Al completarlo con éxito, el método devuelve el
 * ID de la transacción financiera que se acaba de completar.
 */
public interface FinancialCommandService {
    Long handle(CompleteFinancialCommand command);
    Long handle(CancelFinancialTransactionCommand command);
}