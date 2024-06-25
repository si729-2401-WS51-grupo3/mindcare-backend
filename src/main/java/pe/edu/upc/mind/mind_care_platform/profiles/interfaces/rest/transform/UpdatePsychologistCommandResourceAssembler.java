package pe.edu.upc.mind.mind_care_platform.profiles.interfaces.rest.transform;

import pe.edu.upc.mind.mind_care_platform.profiles.domain.model.commands.UpdatePsychologistCommand;
import pe.edu.upc.mind.mind_care_platform.profiles.interfaces.rest.resources.UpdatePsychologistResource;

public class UpdatePsychologistCommandResourceAssembler {
    public static UpdatePsychologistCommand toCommandFromResource(UpdatePsychologistResource resource) {
        return new UpdatePsychologistCommand(resource.psychologistId(), resource.email(), resource.phoneNumber());
    }
}
