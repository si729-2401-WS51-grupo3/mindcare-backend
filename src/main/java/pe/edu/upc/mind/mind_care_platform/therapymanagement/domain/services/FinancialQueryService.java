package pe.edu.upc.mind.mind_care_platform.therapymanagement.domain.services;

import pe.edu.upc.mind.mind_care_platform.therapymanagement.domain.model.aggregates.Financial;
import pe.edu.upc.mind.mind_care_platform.therapymanagement.domain.model.entities.Transaction;
import pe.edu.upc.mind.mind_care_platform.therapymanagement.domain.model.queries.GetAllTransactionsByPatientIdQuery;
import pe.edu.upc.mind.mind_care_platform.therapymanagement.domain.model.queries.GetFinancialIdByPatientIdQuery;
import pe.edu.upc.mind.mind_care_platform.therapymanagement.domain.model.queries.GetAllTransactionsByFinancialId;
import pe.edu.upc.mind.mind_care_platform.therapymanagement.domain.model.queries.GetPatientFinancialQuery;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface FinancialQueryService {
    Optional<Financial> handle(GetPatientFinancialQuery query);
    List<Map<String, Object>> handle(GetFinancialIdByPatientIdQuery query);
    List<Transaction> handle(GetAllTransactionsByPatientIdQuery query);
    List<Transaction> handle(GetAllTransactionsByFinancialId query);
}