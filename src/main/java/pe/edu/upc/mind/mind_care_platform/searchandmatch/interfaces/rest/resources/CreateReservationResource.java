package pe.edu.upc.mind.mind_care_platform.searchandmatch.interfaces.rest.resources;

public record CreateReservationResource(Long id, String reservationTime, String reservationDate, Long patientId) {
}
