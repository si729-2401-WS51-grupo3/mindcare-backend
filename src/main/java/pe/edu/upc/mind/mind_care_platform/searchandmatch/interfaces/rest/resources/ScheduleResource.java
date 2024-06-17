package pe.edu.upc.mind.mind_care_platform.searchandmatch.interfaces.rest.resources;

import pe.edu.upc.mind.mind_care_platform.searchandmatch.domain.model.valueobjects.PsychologistId;

public record ScheduleResource (Long id, Long psychologist, int startedHour, int finishedHour){
}
