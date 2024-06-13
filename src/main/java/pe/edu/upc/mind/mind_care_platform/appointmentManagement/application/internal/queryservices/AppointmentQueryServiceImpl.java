package pe.edu.upc.mind.mind_care_platform.appointmentManagement.application.internal.queryservices;

import org.springframework.stereotype.Service;
import pe.edu.upc.mind.mind_care_platform.appointmentManagement.domain.model.aggregates.Appointment;
import pe.edu.upc.mind.mind_care_platform.appointmentManagement.domain.model.entities.AppointmentDetail;
import pe.edu.upc.mind.mind_care_platform.appointmentManagement.domain.model.queries.*;
import pe.edu.upc.mind.mind_care_platform.appointmentManagement.domain.services.AppointmentQueryService;
import pe.edu.upc.mind.mind_care_platform.appointmentManagement.infrastructure.persistence.jpa.repositories.AppointmentRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AppointmentQueryServiceImpl implements AppointmentQueryService {
    private final AppointmentRepository appointmentRepository;
    public AppointmentQueryServiceImpl(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }
    @Override
    public List<Appointment> handle(GetAllAppointmentsQuery query) {
        return appointmentRepository.findAll();
    }
    @Override
    public Optional<Appointment> handle(GetAppointmentByIdQuery query) {
        return appointmentRepository.findById(query.appointmentId());
    }
    @Override
    public List<Appointment> handle(GetAppointmentByPsychologistIdQuery query) {
        return appointmentRepository.findByPsychologistId(query.psychologistId());
    }
    @Override
    public List<AppointmentDetail> handle (GetAllAppointmentDetailsQuery query) {
        return appointmentRepository.findAll().stream()
                .flatMap(appointment -> appointment.getAppointmentDetails().stream())
                .collect(Collectors.toList());
    }
    @Override
    public Optional<List<AppointmentDetail>> handle(GetAppointmentDetailsByAppointmentIdQuery query) {
        return appointmentRepository.findById(query.appointmentId())
                .map(appointment -> appointment.getAppointmentDetails());
    }
}
