package pe.edu.upc.mind.mind_care_platform.appointmentManagement.interfaces.rest.transform;

import pe.edu.upc.mind.mind_care_platform.appointmentManagement.domain.model.commands.CreateAppointmentCommand;
import pe.edu.upc.mind.mind_care_platform.appointmentManagement.domain.model.valueobjects.PsychologistId;
import pe.edu.upc.mind.mind_care_platform.appointmentManagement.interfaces.rest.resources.CreateAppointmentResource;

public class CreateAppointmentCommandFromResourceAssembler {
public CreateAppointmentCommand toCommandFromResource(CreateAppointmentResource resource){
        return new CreateAppointmentCommand(
            resource.title(),
            resource.type(),
            new PsychologistId(resource.psychologistId())
        );
    }
}
