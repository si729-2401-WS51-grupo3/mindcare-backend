package pe.edu.upc.mind.mind_care_platform.searchandmatch.interfaces.rest.resources;

import pe.edu.upc.mind.mind_care_platform.searchandmatch.domain.model.valueobjects.PsychologistId;

public record ScheduleResource (Long id, int worked_hours, int started_hour, String day, Long psychologistId){
}
