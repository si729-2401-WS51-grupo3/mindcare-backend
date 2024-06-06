package pe.edu.upc.mind.mind_care_platform.therapymanagement.interfaces.rest.resources;

import lombok.Getter;
import lombok.Setter;

public record AddTransactionToFinancialResource(Long patientId, Long pyschologistId, Long reservationId, Integer amount) {
}