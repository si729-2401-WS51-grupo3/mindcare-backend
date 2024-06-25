package pe.edu.upc.mind.mind_care_platform.profiles.interfaces.rest.resources;

public record CreatePatientResource(String firstName, String lastName, String email, String phone, String photoUrl, String occupation, String description, String gender, String birthDate) {
}
