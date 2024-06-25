package pe.edu.upc.mind.mind_care_platform.profiles.interfaces.rest.resources;

public record CreatePsychologistResource(Long id, String firstName, String lastName, String email, String phone, String photoUrl, String occupation, String description, String gender, String birthDate, Integer scheduleId) {
}
