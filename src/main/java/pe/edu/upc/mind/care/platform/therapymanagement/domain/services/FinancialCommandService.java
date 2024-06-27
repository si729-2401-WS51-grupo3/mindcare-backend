package pe.edu.upc.mind.care.platform.therapymanagement.domain.services;


import pe.edu.upc.mind.care.platform.therapymanagement.domain.model.aggregates.Financial;
import pe.edu.upc.mind.care.platform.therapymanagement.domain.model.commands.AddTransactionToFinancialCommand;
import pe.edu.upc.mind.care.platform.therapymanagement.domain.model.commands.CancelTransactionCommand;

public interface FinancialCommandService {
    Financial handle(AddTransactionToFinancialCommand command);
    void handle(CancelTransactionCommand command);
}