package pe.edu.upc.mind.mind_care_platform.appointmentManagement.domain.services;

import pe.edu.upc.mind.mind_care_platform.appointmentManagement.domain.model.commands.CreateAppointmentCommand;
import pe.edu.upc.mind.mind_care_platform.appointmentManagement.domain.model.commands.AddAppointmentDataToAppointmentCommand;

public interface AppointmentCommandService {
    Long handle(CreateAppointmentCommand command);
    void handle(AddAppointmentDataToAppointmentCommand command);
}