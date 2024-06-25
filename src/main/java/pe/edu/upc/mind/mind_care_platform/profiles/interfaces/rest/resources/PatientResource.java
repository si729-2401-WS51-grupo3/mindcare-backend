package pe.edu.upc.mind.mind_care_platform.profiles.interfaces.rest.resources;

public record PatientResource(Long id, String fullName, String email, String phone, String photoUrl, String occupation, String description, String gender, String birthDate){
}
