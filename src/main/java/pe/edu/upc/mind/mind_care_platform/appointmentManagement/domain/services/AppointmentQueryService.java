package pe.edu.upc.mind.mind_care_platform.appointmentManagement.domain.services;

import pe.edu.upc.mind.mind_care_platform.appointmentManagement.domain.model.aggregates.Appointment;
import pe.edu.upc.mind.mind_care_platform.appointmentManagement.domain.model.queries.GetAppointmentQuery;
import pe.edu.upc.mind.mind_care_platform.appointmentManagement.domain.model.queries.GetAllAppointmentsQuery;

import java.util.List;
import java.util.Optional;

public interface AppointmentQueryService {
    Optional<Appointment> handle(GetAppointmentQuery query);
    List<Appointment> handle(GetAllAppointmentsQuery query);
}