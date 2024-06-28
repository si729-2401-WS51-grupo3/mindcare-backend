package pe.edu.upc.mind.care.platform.iam.interfaces.rest.resources;

import java.util.List;

public record SignUpResource(String Email, String password, List<String> roles) {
}

