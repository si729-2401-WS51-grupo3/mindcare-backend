package pe.edu.upc.mind.mind_care_platform.appointmentManagement.domain.services;

import pe.edu.upc.mind.mind_care_platform.appointmentManagement.domain.model.aggregates.Appointment;
import pe.edu.upc.mind.mind_care_platform.appointmentManagement.domain.model.entities.AppointmentDetail;
import pe.edu.upc.mind.mind_care_platform.appointmentManagement.domain.model.queries.*;

import java.util.List;
import java.util.Optional;

public interface AppointmentQueryService {
    List<Appointment> handle(GetAllAppointmentsQuery query);
    Optional<Appointment> handle(GetAppointmentByIdQuery query);
    List<Appointment> handle(GetAppointmentByPsychologistIdQuery query);
    List<AppointmentDetail> handle(GetAllAppointmentDetailsQuery query);
    Optional<List<AppointmentDetail>> handle(GetAppointmentDetailsByAppointmentIdQuery query);

}
