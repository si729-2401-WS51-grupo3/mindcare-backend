package pe.edu.upc.mind.care.platform.profiles.interfaces.rest.transform;

import pe.edu.upc.mind.care.platform.profiles.domain.model.commands.CreateProfileCommand;
import pe.edu.upc.mind.care.platform.profiles.interfaces.rest.resources.CreateProfileResource;

public class CreateProfileCommandFromResourceAssembler {
  public static CreateProfileCommand toCommandFromResource(CreateProfileResource resource) {
    return new CreateProfileCommand(resource.firstName(), resource.lastName(),
        resource.email(), resource.street(), resource.number(), resource.city(),
        resource.postalCode(), resource.country());
  }
}
