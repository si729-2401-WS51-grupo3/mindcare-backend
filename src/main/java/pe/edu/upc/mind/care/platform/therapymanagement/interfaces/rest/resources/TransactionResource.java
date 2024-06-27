package pe.edu.upc.mind.care.platform.therapymanagement.interfaces.rest.resources;

public record TransactionResource(Long id, Long patientId, Long pyschologistId, Long reservationId, int amount) {
}