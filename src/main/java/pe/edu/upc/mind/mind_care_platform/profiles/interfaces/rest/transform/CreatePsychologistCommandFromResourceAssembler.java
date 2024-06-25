package pe.edu.upc.mind.mind_care_platform.profiles.interfaces.rest.transform;

import pe.edu.upc.mind.mind_care_platform.profiles.domain.model.commands.CreatePsychologistCommand;
import pe.edu.upc.mind.mind_care_platform.profiles.interfaces.rest.resources.CreatePsychologistResource;

public class CreatePsychologistCommandFromResourceAssembler {
    public static CreatePsychologistCommand toCommandFromResource(CreatePsychologistResource resource) {
        return new CreatePsychologistCommand(
                resource.firstName(),
                resource.lastName(),
                resource.email(),
                resource.phone(),
                resource.photoUrl(),
                resource.occupation(),
                resource.description(),
                resource.gender(),
                resource.birthDate(),
                resource.scheduleId()
        );
    }
}
