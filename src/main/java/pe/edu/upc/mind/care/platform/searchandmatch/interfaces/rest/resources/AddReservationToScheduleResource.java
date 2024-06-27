package pe.edu.upc.mind.care.platform.searchandmatch.interfaces.rest.resources;

public record AddReservationToScheduleResource(Long scheduleId, Long patientId, Long psychologistId, int reservationHour, String reservationDate){
}