package pe.edu.upc.mind.mind_care_platform.profiles.domain.model.commands;

public record UpdatePatientCommand(Long id, String email, String phone) {
}
