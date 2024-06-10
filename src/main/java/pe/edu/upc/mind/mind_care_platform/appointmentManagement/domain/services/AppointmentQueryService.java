package pe.edu.upc.mind.mind_care_platform.appointmentManagement.domain.services;

import pe.edu.upc.mind.mind_care_platform.appointmentManagement.domain.model.aggregates.Appointment;
import pe.edu.upc.mind.mind_care_platform.appointmentManagement.domain.model.entities.AppointmentData;
import pe.edu.upc.mind.mind_care_platform.appointmentManagement.domain.model.queries.GetAppointmentByIdQuery;
import pe.edu.upc.mind.mind_care_platform.appointmentManagement.domain.model.queries.GetAllAppointmentsQuery;
import pe.edu.upc.mind.mind_care_platform.appointmentManagement.domain.model.queries.GetAppointmentDataByAppointmentIdQuery;

import java.util.List;
import java.util.Optional;

public interface AppointmentQueryService {
    Optional<Appointment> handle(GetAppointmentByIdQuery query);
    List<Appointment> handle(GetAllAppointmentsQuery query);
    Optional<AppointmentData> handle(GetAppointmentDataByAppointmentIdQuery query);
}