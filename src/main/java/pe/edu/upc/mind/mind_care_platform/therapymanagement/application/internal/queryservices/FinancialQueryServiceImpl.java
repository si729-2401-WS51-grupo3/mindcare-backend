package pe.edu.upc.mind.mind_care_platform.therapymanagement.application.internal.queryservices;

import org.springframework.stereotype.Service;

import pe.edu.upc.mind.mind_care_platform.therapymanagement.domain.model.aggregates.Financial;
import pe.edu.upc.mind.mind_care_platform.therapymanagement.domain.model.queries.GetAllFinancialTransactionByPatientIdQuery;
import pe.edu.upc.mind.mind_care_platform.therapymanagement.domain.model.queries.GetAllFinancialTransactionByPsychologistIdQuery;
import pe.edu.upc.mind.mind_care_platform.therapymanagement.domain.model.queries.GetAllFinancialTransactionQuery;
import pe.edu.upc.mind.mind_care_platform.therapymanagement.domain.model.queries.GetFinancialTransactionByIdQuery;
import pe.edu.upc.mind.mind_care_platform.therapymanagement.domain.model.valueobjects.PatientId;
import pe.edu.upc.mind.mind_care_platform.therapymanagement.domain.model.valueobjects.PyschologistId;
import pe.edu.upc.mind.mind_care_platform.therapymanagement.domain.services.FinancialQueryService;
import pe.edu.upc.mind.mind_care_platform.therapymanagement.infraestructure.persistence.jpa.repositories.FinancialRepository;

import java.util.List;
import java.util.Optional;


@Service
public class FinancialQueryServiceImpl implements FinancialQueryService {

    private final FinancialRepository financialRepository;

    public FinancialQueryServiceImpl(FinancialRepository financialRepository) {
        this.financialRepository = financialRepository;
    }

    /**
     * Query handler to get a financial transaction by its ID.
     *
     * @param query containing the transactionId of the financial transaction to be retrieved.
     * @return Optional<Financial> - The Financial object if the transactionId is found,otherwise Optional.empty().
     */
    @Override
    public Optional<Financial> handle(GetFinancialTransactionByIdQuery query) {
        return financialRepository.findById(query.transactionId());
    }

    @Override
    public List<Financial> handle(GetAllFinancialTransactionQuery query) {
        return financialRepository.findAll();
    }

    /**
     * Query handler to get all financial transactions by a patient's ID.
     *
     * @param query containing the patientId of the patient whose financial transactions are to be retrieved.
     * @return List<Financial> - The list of Financial objects if the patientId is found, otherwise an empty list.
     */
    @Override
    public List<Financial> handle(GetAllFinancialTransactionByPatientIdQuery query) {
        return financialRepository.findAllByPatientId(new PatientId(query.patientId()));
    }

    /**
     * Query handler to get all financial transactions by a psychologist's ID.
     *
     * @param query containing the psychologistId of the psychologist whose financial transactions are to be retrieved.
     * @return List<Financial> - The list of Financial objects if the psychologistId is found, otherwise an empty list.
     */
    @Override
    public List<Financial> handle(GetAllFinancialTransactionByPsychologistIdQuery query) {
        return financialRepository.findAllByPyschologistId(new PyschologistId(query.psychologistId()));
    }
}
