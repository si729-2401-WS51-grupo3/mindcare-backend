package pe.edu.upc.mind.mind_care_platform.therapymanagement.domain.model.commands;

public record UpdatedFinancialTransactionCommand(Long transactionId, Integer amount) {
}
