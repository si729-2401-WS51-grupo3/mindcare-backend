package pe.edu.upc.mind.mind_care_platform.therapymanagement.domain.services;

import pe.edu.upc.mind.mind_care_platform.therapymanagement.domain.model.commands.AddTransactionToFinancialCommand;
import pe.edu.upc.mind.mind_care_platform.therapymanagement.domain.model.commands.CancelTransactionCommand;


public interface FinancialCommandService {
    void handle(AddTransactionToFinancialCommand command);
    void handle(CancelTransactionCommand command);

}