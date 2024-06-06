package pe.edu.upc.mind.mind_care_platform.therapymanagement.application.internal.queryservices;

import org.springframework.stereotype.Service;

import pe.edu.upc.mind.mind_care_platform.therapymanagement.domain.model.aggregates.Financial;
import pe.edu.upc.mind.mind_care_platform.therapymanagement.domain.model.entities.Transaction;
import pe.edu.upc.mind.mind_care_platform.therapymanagement.domain.model.queries.GetAllTransactionsByFinancialId;
import pe.edu.upc.mind.mind_care_platform.therapymanagement.domain.model.queries.GetAllTransactionsByPatientIdQuery;
import pe.edu.upc.mind.mind_care_platform.therapymanagement.domain.model.queries.GetFinancialIdByPatientIdQuery;
import pe.edu.upc.mind.mind_care_platform.therapymanagement.domain.model.queries.GetPatientFinancialQuery;
import pe.edu.upc.mind.mind_care_platform.therapymanagement.domain.services.FinancialQueryService;
import pe.edu.upc.mind.mind_care_platform.therapymanagement.infraestructure.persistence.jpa.repositories.FinancialRepository;

import java.util.List;
import java.util.Map;
import java.util.Optional;


@Service
public class FinancialQueryServiceImpl implements FinancialQueryService {
    private final FinancialRepository financialRepository;

    public FinancialQueryServiceImpl(FinancialRepository financialRepository) {
        this.financialRepository = financialRepository;
    }
    @Override
    public Optional<Financial> handle(GetPatientFinancialQuery query) {
        return financialRepository.findById(query.financialId());
    }

    @Override
    public List<Map<String, Object>> handle(GetFinancialIdByPatientIdQuery query) {
        return List.of();
    }

    @Override
    public List<Map<String, Object>> handle(GetAllTransactionsByPatientIdQuery query) {
        return List.of();
    }

    @Override
    public List<Transaction> handle(GetAllTransactionsByFinancialId query) {
        return List.of();
    }
}