package pe.edu.upc.mind.mind_care_platform.appointmentManagement.interfaces.rest.transform;

import pe.edu.upc.mind.mind_care_platform.appointmentManagement.domain.model.commands.AddAppointmentDetailToAppointmentCommand;

public class AddAppointmentDetailCommandFromResourceAssembler {
    public AddAppointmentDetailToAppointmentCommand toCommandFromResource(AddAppointmentDetailToAppointmentCommand resource){
        return new AddAppointmentDetailToAppointmentCommand(
            resource.appointmentId(),
            resource.description()
        );
    }
}
