package pe.edu.upc.mind.mind_care_platform.profiles.interfaces.rest.resources;

public record UpdatePatientResource(Long patientId, String email, String phoneNumber) {
}
