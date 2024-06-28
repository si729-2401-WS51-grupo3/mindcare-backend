package pe.edu.upc.mind.care.platform.iam.interfaces.rest.resources;

public record AuthenticatedUserResource(Long id, String Email, String token) {
}
