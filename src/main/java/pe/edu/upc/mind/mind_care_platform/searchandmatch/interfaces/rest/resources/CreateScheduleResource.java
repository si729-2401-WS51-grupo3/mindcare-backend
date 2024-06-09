package pe.edu.upc.mind.mind_care_platform.searchandmatch.interfaces.rest.resources;

public record CreateScheduleResource (int worked_hours, int started_hour, String day, Long psychologistId){
}
