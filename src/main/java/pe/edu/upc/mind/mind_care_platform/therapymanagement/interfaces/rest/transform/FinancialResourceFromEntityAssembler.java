package pe.edu.upc.mind.mind_care_platform.therapymanagement.interfaces.rest.transform;

import pe.edu.upc.mind.mind_care_platform.therapymanagement.domain.model.aggregates.Financial;
import pe.edu.upc.mind.mind_care_platform.therapymanagement.domain.model.valueobjects.PatientId;
import pe.edu.upc.mind.mind_care_platform.therapymanagement.domain.model.valueobjects.PyschologistId;
import pe.edu.upc.mind.mind_care_platform.therapymanagement.interfaces.rest.resources.FinancialResource;
/**
 * Utilizamos el patron de Assembler  para transformar tus entidades de dominio (Financial) en recursos (FinancialResource)
 */
public class FinancialResourceFromEntityAssembler {
    public static FinancialResource toResourceFromEntity(Financial financial){
        return new FinancialResource(financial.getId(), financial.getPatientId(), financial.getPyschologistId());
    };

}
