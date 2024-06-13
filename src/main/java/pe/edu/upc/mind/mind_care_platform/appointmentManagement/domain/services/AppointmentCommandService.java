package pe.edu.upc.mind.mind_care_platform.appointmentManagement.domain.services;

import pe.edu.upc.mind.mind_care_platform.appointmentManagement.domain.model.aggregates.Appointment;
import pe.edu.upc.mind.mind_care_platform.appointmentManagement.domain.model.commands.*;

import java.util.Optional;

public interface AppointmentCommandService {
    Appointment handle(CreateAppointmentCommand command);
    Optional<Appointment> handle(UpdateAppointmentCommand query);
    void handle(DeleteAppointmentCommand query);
    Appointment handle(AddAppointmentDetailToAppointmentCommand query);
    void handle(AssignPsychologistToAppointmentCommand query);
}
