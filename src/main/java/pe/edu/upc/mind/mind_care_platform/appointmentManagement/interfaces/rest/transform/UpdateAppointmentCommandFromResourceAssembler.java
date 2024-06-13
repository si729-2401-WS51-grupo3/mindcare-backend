package pe.edu.upc.mind.mind_care_platform.appointmentManagement.interfaces.rest.transform;

import pe.edu.upc.mind.mind_care_platform.appointmentManagement.domain.model.commands.UpdateAppointmentCommand;
import pe.edu.upc.mind.mind_care_platform.appointmentManagement.domain.model.valueobjects.PsychologistId;
import pe.edu.upc.mind.mind_care_platform.appointmentManagement.interfaces.rest.resources.UpdateAppointmentResource;

public class UpdateAppointmentCommandFromResourceAssembler {
public static UpdateAppointmentCommand toCommandFromResource(UpdateAppointmentResource resource) {
        return new UpdateAppointmentCommand(
                resource.appointmentId(),
                resource.title(),
                resource.type(),
                new PsychologistId(resource.psychologistId())
        );
    }
}
