package pe.edu.upc.mind.mind_care_platform.searchandmatch.domain.model.commands;

import pe.edu.upc.mind.mind_care_platform.searchandmatch.domain.model.valueobjects.HourStatus;

public record UpdateScheduleHourStatusCommand (Long id, int hour, HourStatus status){
}
