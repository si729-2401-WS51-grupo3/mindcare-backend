package pe.edu.upc.mind.mind_care_platform.appointmentManagement.domain.services;

import pe.edu.upc.mind.mind_care_platform.appointmentManagement.domain.model.aggregates.Appointment;
import pe.edu.upc.mind.mind_care_platform.appointmentManagement.domain.model.commands.CreateAppointmentCommand;
import pe.edu.upc.mind.mind_care_platform.appointmentManagement.domain.model.commands.UpdateAppointmentCommand;
import pe.edu.upc.mind.mind_care_platform.appointmentManagement.domain.model.commands.CancelAppointmentCommand;

import java.util.Optional;

public interface AppointmentCommandService {
    Long handle(CreateAppointmentCommand command);
    Optional<Appointment> handle(UpdateAppointmentCommand command);
    void handle(CancelAppointmentCommand command);
}