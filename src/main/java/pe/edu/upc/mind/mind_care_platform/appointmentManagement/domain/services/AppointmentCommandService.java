package pe.edu.upc.mind.mind_care_platform.appointmentManagement.domain.services;

import pe.edu.upc.mind.mind_care_platform.appointmentManagement.domain.model.commands.CreateAppointmentCommand;

public interface AppointmentCommandService {
    Long handle(CreateAppointmentCommand command);
}
