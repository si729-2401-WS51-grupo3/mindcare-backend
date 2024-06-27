package pe.edu.upc.mind.care.platform.therapymanagement.interfaces.rest.resources;

public record CancelTransactionCommandResource(Long patientId, Long transactionId ) {
}