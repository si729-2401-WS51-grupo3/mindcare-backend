package pe.edu.upc.mind.mind_care_platform.searchandmatch.domain.model.commands;

import pe.edu.upc.mind.mind_care_platform.searchandmatch.domain.model.entities.Schedule;

public record UpdateScheduleCommand(Long Id, String name, int worked_hours, int started_hour) {
}