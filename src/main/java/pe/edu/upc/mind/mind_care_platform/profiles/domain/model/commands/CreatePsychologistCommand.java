package pe.edu.upc.mind.mind_care_platform.profiles.domain.model.commands;


public record CreatePsychologistCommand(String firstName, String lastName, String email, String phone, String photoUrl, String occupation, String description, String gender, String birthDate, Integer scheduleId) {
}