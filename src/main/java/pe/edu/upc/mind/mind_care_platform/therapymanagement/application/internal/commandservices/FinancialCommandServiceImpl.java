package pe.edu.upc.mind.mind_care_platform.therapymanagement.application.internal.commandservices;

import org.springframework.stereotype.Service;
import pe.edu.upc.mind.mind_care_platform.therapymanagement.domain.model.aggregates.Financial;
import pe.edu.upc.mind.mind_care_platform.therapymanagement.domain.model.commands.CancelFinancialTransactionCommand;
import pe.edu.upc.mind.mind_care_platform.therapymanagement.domain.model.commands.CreateFinancialTransactionCommand;
import pe.edu.upc.mind.mind_care_platform.therapymanagement.domain.model.commands.PayFinancialTransactionCommand;
import pe.edu.upc.mind.mind_care_platform.therapymanagement.domain.model.commands.UpdatedFinancialTransactionCommand;
import pe.edu.upc.mind.mind_care_platform.therapymanagement.domain.model.entities.Transaction;
import pe.edu.upc.mind.mind_care_platform.therapymanagement.domain.services.FinancialCommandService;
import pe.edu.upc.mind.mind_care_platform.therapymanagement.infraestructure.persistence.jpa.repositories.FinancialRepository;

import java.util.Optional;

@Service
public class FinancialCommandServiceImpl implements FinancialCommandService{
    private final FinancialRepository financialRepository;
    public FinancialCommandServiceImpl(FinancialRepository financialRepository) {
        this.financialRepository = financialRepository;
    }

    @Override
    public Long handle(CreateFinancialTransactionCommand command) {
        Financial financial = new Financial(command);
        financialRepository.save(financial);
        return financial.getId();
    }

    /**
     * Command handler to update the amount of a specific transaction within a financial transaction.
     *
     * @param command containing the transactionId of the financial transaction and the new amount to be updated.
     *
     * @return Optional<Financial> - The updated Financial object if the transactionId is found, otherwise an exception is thrown.
     *
     * The method works as follows:
     * 1. It tries to find the Financial object with the provided transactionId from the command.
     * 2. If the Financial object is found, it then tries to find the specific Transaction within the Financial object's transactions list using the same transactionId.
     * 3. If the Transaction is found, it updates the amount of the Transaction with the amount provided in the command.
     * 4. The updated Financial object is then saved back to the repository.
     * 5. Finally, the updated Financial object is returned.
     *
     * If at any point the Financial object or the Transaction is not found, a RuntimeException is thrown.
     */
    @Override
    public Optional<Financial> handle(UpdatedFinancialTransactionCommand command) {
        return financialRepository.findById(command.transactionId()).map(financial -> {
            Transaction transaction = financial.getTransactions().stream()
                    .filter(t -> t.getId().equals(command.transactionId()))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("Transaction not found"));

            transaction.updateAmount(command.amount());
            financialRepository.save(financial);
            return financial;
        });
    }
    /**
     * Command handler to pay the financial transaction
     * @param command containing transactionId
     * @return transactionId
     */
    @Override
    public Long handle(PayFinancialTransactionCommand command) {
        financialRepository.findById(command.transactionId()).map(financial -> {
            financial.pay();
            financialRepository.save(financial);
            return command.transactionId();
        }).orElseThrow(() -> new RuntimeException("Financial transaction not found"));
        return null;
    }

    /**
     * Command handler to cancel the financial transaction
     * @param command containing transactionId
     * @return transactionId
     */
    @Override
    public Long handle(CancelFinancialTransactionCommand command) {
        financialRepository.findById(command.transactionId()).map(financial -> {
            financial.cancel();
            financialRepository.save(financial);
            return command.transactionId();
        }).orElseThrow(() -> new RuntimeException("Financial transaction not found"));
        return null;
    }
}