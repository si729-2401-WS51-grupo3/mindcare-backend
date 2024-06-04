package pe.edu.upc.mind.mind_care_platform.therapymanagement.domain.services;

import pe.edu.upc.mind.mind_care_platform.therapymanagement.domain.model.aggregates.Financial;
import pe.edu.upc.mind.mind_care_platform.therapymanagement.domain.model.entities.Transaction;
import pe.edu.upc.mind.mind_care_platform.therapymanagement.domain.model.queries.GetAllFinancialTransactionByPatientIdQuery;
import pe.edu.upc.mind.mind_care_platform.therapymanagement.domain.model.queries.GetAllFinancialTransactionByPsychologistIdQuery;
import pe.edu.upc.mind.mind_care_platform.therapymanagement.domain.model.queries.GetAllFinancialTransactionQuery;
import pe.edu.upc.mind.mind_care_platform.therapymanagement.domain.model.queries.GetFinancialTransactionByIdQuery;
import pe.edu.upc.mind.mind_care_platform.therapymanagement.domain.model.valueobjects.PatientId;
import pe.edu.upc.mind.mind_care_platform.therapymanagement.domain.model.valueobjects.PyschologistId;

import java.util.List;
import java.util.Optional;

public interface FinancialQueryService {

    Optional<Financial> handle(GetFinancialTransactionByIdQuery query);
    List<Financial> handle(GetAllFinancialTransactionQuery query);

    List<Financial> handle(GetAllFinancialTransactionByPatientIdQuery query);

    List<Financial> handle(GetAllFinancialTransactionByPsychologistIdQuery query);
}