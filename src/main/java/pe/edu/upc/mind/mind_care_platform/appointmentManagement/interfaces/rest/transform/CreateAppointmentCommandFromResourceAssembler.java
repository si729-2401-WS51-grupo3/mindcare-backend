package pe.edu.upc.mind.mind_care_platform.appointmentManagement.interfaces.rest.transform;

import pe.edu.upc.mind.mind_care_platform.appointmentManagement.domain.model.commands.CreateAppointmentCommand;
import pe.edu.upc.mind.mind_care_platform.appointmentManagement.interfaces.rest.resources.AppointmentResource;

public class CreateAppointmentCommandFromResourceAssembler {

    public static CreateAppointmentCommand toCommandFromResource(AppointmentResource resource) {
        return new CreateAppointmentCommand(
            resource.sessionName()
        );
    }
}
