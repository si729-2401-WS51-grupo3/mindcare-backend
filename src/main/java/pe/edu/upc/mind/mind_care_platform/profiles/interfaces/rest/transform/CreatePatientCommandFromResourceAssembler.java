package pe.edu.upc.mind.mind_care_platform.profiles.interfaces.rest.transform;

import pe.edu.upc.mind.mind_care_platform.profiles.domain.model.commands.CreatePatientCommand;
import pe.edu.upc.mind.mind_care_platform.profiles.interfaces.rest.resources.CreatePatientResource;

public class CreatePatientCommandFromResourceAssembler {
    public static CreatePatientCommand toCommandFromResource(CreatePatientResource resource) {
        return new CreatePatientCommand(resource.firstName(), resource.lastName(),
                resource.email(), resource.phone(), resource.photoUrl(), resource.occupation(),
                resource.description(), resource.gender(), resource.birthDate());
    }
}
