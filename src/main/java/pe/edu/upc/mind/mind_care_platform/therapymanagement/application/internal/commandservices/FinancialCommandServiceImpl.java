package pe.edu.upc.mind.mind_care_platform.therapymanagement.application.internal.commandservices;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.upc.mind.mind_care_platform.therapymanagement.domain.model.aggregates.Financial;
import pe.edu.upc.mind.mind_care_platform.therapymanagement.domain.model.commands.AddTransactionToFinancialCommand;
import pe.edu.upc.mind.mind_care_platform.therapymanagement.domain.model.commands.CancelTransactionCommand;
import pe.edu.upc.mind.mind_care_platform.therapymanagement.domain.model.entities.Transaction;
import pe.edu.upc.mind.mind_care_platform.therapymanagement.domain.model.valueobjects.PatientId;
import pe.edu.upc.mind.mind_care_platform.therapymanagement.domain.model.valueobjects.PyschologistId;
import pe.edu.upc.mind.mind_care_platform.therapymanagement.domain.model.valueobjects.ReservationId;
import pe.edu.upc.mind.mind_care_platform.therapymanagement.domain.services.FinancialCommandService;
import pe.edu.upc.mind.mind_care_platform.therapymanagement.infraestructure.persistence.jpa.repositories.FinancialRepository;

import java.util.List;

@Service
public class FinancialCommandServiceImpl implements FinancialCommandService {
    private final FinancialRepository financialRepository;

    public FinancialCommandServiceImpl(FinancialRepository financialRepository) {
        this.financialRepository = financialRepository;
    }

    /**
     * Este metodo se encarga de agregar una transaccion a la lista de finanzas de un paciente
     */

    @Override
    public void handle(AddTransactionToFinancialCommand command) {
        Long patientId = command.patientId();
        Financial financial;
        List<Financial> financials = financialRepository.findByPatientId(patientId);
        if (financials.isEmpty()) {
            financial = new Financial();
            financial.setPatientId(patientId);
        } else {
            financial = financials.get(0); // Tomamos el primer Financial si hay más de uno
        }
        PatientId patientIdInstance = new PatientId(patientId);
        PyschologistId pyschologistIdInstance = new PyschologistId(command.pyschologistId());
        ReservationId reservationIdInstance = new ReservationId(command.reservationId());
        int amount = command.getAmount();
        Transaction newTransaction = new Transaction(patientIdInstance, financial, pyschologistIdInstance, reservationIdInstance, amount);
        financial.addTransaction(newTransaction);
        financialRepository.save(financial);
    }

    @Override
    @Transactional
    public void handle(CancelTransactionCommand command) {
        Long patientId = command.patientId();
        List<Financial> financials = financialRepository.findByPatientId(patientId);
        if (financials.isEmpty()) {
            throw new IllegalArgumentException("Financial not found");
        }
        Financial financial = financials.getFirst(); // Tomamos el primer Financial si hay más de uno
        Transaction transactionToRemove = financial.getTransactions().stream()
                .filter(transaction -> transaction.getId().equals(command.transactionId()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Transaction not found"));
        financial.removeTransaction(transactionToRemove);
        financialRepository.save(financial);
    }

}