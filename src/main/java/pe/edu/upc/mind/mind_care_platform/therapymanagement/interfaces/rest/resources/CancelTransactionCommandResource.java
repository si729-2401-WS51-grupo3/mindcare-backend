package pe.edu.upc.mind.mind_care_platform.therapymanagement.interfaces.rest.resources;

public record CancelTransactionCommandResource(Long patientId, Long transactionId ) {
}