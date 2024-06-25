package pe.edu.upc.mind.mind_care_platform.profiles.domain.model.commands;

public record UpdatePsychologistCommand(Long id, String email, String phone) {
}