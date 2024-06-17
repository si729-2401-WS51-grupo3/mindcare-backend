package pe.edu.upc.mind.mind_care_platform.searchandmatch.interfaces.rest.resources;

public record AddReservationToScheduleResource(Long scheduleId, Long patientId, Long psychologistId, int reservationHour, String reservationDate){
}