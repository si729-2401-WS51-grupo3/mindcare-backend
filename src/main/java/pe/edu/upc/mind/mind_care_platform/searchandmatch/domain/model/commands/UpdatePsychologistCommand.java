package pe.edu.upc.mind.mind_care_platform.searchandmatch.domain.model.commands;

public record UpdatePsychologistCommand(Long id, String name, int worked_hours, int started_hour) {
}
