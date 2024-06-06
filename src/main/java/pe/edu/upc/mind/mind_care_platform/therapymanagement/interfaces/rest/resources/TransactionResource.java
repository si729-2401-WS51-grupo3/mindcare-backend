package pe.edu.upc.mind.mind_care_platform.therapymanagement.interfaces.rest.resources;

public record TransactionResource(Long id, Long patientId, Long pyschologistId, Long reservationId, int amount) {
}