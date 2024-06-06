package pe.edu.upc.mind.mind_care_platform.therapymanagement.interfaces.rest.resources;

import java.util.List;

public record FinancialResource(Long id, Long patientId, List<TransactionResource> transactions) {
}