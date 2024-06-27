package pe.edu.upc.mind.care.platform.searchandmatch.interfaces.rest.resources;

public record ReservationResource(Long id, Long patientId, Long psychologistId, int reservationHour, String reservationDate){
}
