package pe.edu.upc.mind.care.platform.appointmentManagement.domain.services;


import pe.edu.upc.mind.care.platform.appointmentManagement.domain.model.commands.CreateAppointmentCommand;

public interface AppointmentCommandService {
    Long handle(CreateAppointmentCommand command);
}
