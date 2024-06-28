package pe.edu.upc.mind.care.platform.iam.interfaces.rest.resources;

import java.util.List;

public record UserResource(Long id, String Email, List<String> roles) {
}
