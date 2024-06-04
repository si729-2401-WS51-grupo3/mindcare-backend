package pe.edu.upc.mind.mind_care_platform.searchandmatch.domain.model.commands;

public record UpdatePsychologistScheduleCommand(Long id, int worked_hours, int started_hour) {
}