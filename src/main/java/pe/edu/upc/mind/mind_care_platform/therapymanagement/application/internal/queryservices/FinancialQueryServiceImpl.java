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

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@Service
public class FinancialQueryServiceImpl implements FinancialQueryService {
    private final FinancialRepository financialRepository;

    public FinancialQueryServiceImpl(FinancialRepository financialRepository) {
        this.financialRepository = financialRepository;
    }

    /**
     *  Recupera la informacion de la finanza por id del paciente
     */
    @Override
    public Optional<Financial> handle(GetPatientFinancialQuery query) {
        return financialRepository.findById(query.financialId());
    }

    /**
     * Este metodo se encarga de obtener el id de las finanzas gracias al id de un paciente
     */
    @Override
    public List<Map<String, Object>> handle(GetFinancialIdByPatientIdQuery query) {
        List<Financial> financials = financialRepository.findByPatientId(query.patientId());
        if (!financials.isEmpty()) {
            Financial financial = financials.get(0); // Tomamos el primer Financial si hay más de uno
            Map<String, Object> financialMap = new HashMap<>();
            financialMap.put("financial_id", financial.getId());
            return List.of(financialMap);
        } else {
            return List.of();
        }
    }

    /**
     * Se utiliza para obtener todas las transacciones asociadas a un financialId
     */
    @Override
    public List<Transaction> handle(GetAllTransactionsByFinancialId query) {
        return financialRepository.findAllTransactionsByFinancialId(query.financialId());
    }

    /**
     * Se utiliza para obtener todas las transacciones asociadas a un patientId
     */
    @Override
    public List<Transaction> handle(GetAllTransactionsByPatientIdQuery query) {
        return financialRepository.findAllTransactionsByPatientId(query.patientId());
    }
}