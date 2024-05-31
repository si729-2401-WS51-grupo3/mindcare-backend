package pe.edu.upc.mind.mind_care_platform.therapymanagement.domain.model.services;

import pe.edu.upc.mind.mind_care_platform.therapymanagement.domain.model.aggregates.Financial;
import pe.edu.upc.mind.mind_care_platform.therapymanagement.domain.model.entities.MeetingDetails;
import pe.edu.upc.mind.mind_care_platform.therapymanagement.domain.model.queries.getFinancialByMeetingId;

/**
 * el paciente solo realiza el pago y no
 * necesita obtener detalles del mismo por eso VOID
 */
public interface FinancialQueryService {
    void handle(Long financialId);
    //por se acaso sea necesario devolver una fucnion que vuelva
    //un objeto de tipo Financial
    Financial getFinancialByMeetingId(getFinancialByMeetingId query);
}
