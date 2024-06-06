package pe.edu.upc.mind.mind_care_platform.therapymanagement.interfaces.rest.resources;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AddTransactionToFinancialResource {
    private Long patientId;
    @Getter
    private Long financialId;
    private Long pyschologistId;
    private Long reservationId;
    private Integer amount;

}