package pe.edu.upc.mind.mind_care_platform.profiles.interfaces.rest.transform;

import pe.edu.upc.mind.mind_care_platform.profiles.domain.model.commands.UpdatePatientCommand;
import pe.edu.upc.mind.mind_care_platform.profiles.interfaces.rest.resources.UpdatePatientResource;

public class UpdatePatientCommandResourceAssembler {
    public static UpdatePatientCommand toCommandFromResource(UpdatePatientResource resource) {
        return new UpdatePatientCommand(resource.patientId(), resource.email(), resource.phoneNumber());
    }
}
