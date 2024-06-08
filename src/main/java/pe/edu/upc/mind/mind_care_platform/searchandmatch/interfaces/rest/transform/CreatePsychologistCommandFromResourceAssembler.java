package pe.edu.upc.mind.mind_care_platform.searchandmatch.interfaces.rest.transform;

import pe.edu.upc.mind.mind_care_platform.searchandmatch.domain.model.commands.CreatePsychologistCommand;
import pe.edu.upc.mind.mind_care_platform.searchandmatch.interfaces.rest.resources.CreatePsychologistResource;

public class CreatePsychologistCommandFromResourceAssembler {
    public static CreatePsychologistCommand toCommandFromResource(CreatePsychologistResource resource) {
        return new CreatePsychologistCommand(resource.name(), resource.worked_hours(), resource.started_hour());
    }
}
