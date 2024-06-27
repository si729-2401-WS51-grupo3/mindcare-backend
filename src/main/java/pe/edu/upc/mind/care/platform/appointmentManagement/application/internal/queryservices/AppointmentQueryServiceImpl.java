package pe.edu.upc.mind.care.platform.appointmentManagement.application.internal.queryservices;

import org.springframework.stereotype.Service;
import pe.edu.upc.mind.care.platform.appointmentManagement.domain.model.aggregates.Appointment;
import pe.edu.upc.mind.care.platform.appointmentManagement.domain.model.queries.GetAllAppointmentsQuery;
import pe.edu.upc.mind.care.platform.appointmentManagement.domain.model.queries.GetAppointmentByIdQuery;
import pe.edu.upc.mind.care.platform.appointmentManagement.domain.services.AppointmentQueryService;
import pe.edu.upc.mind.care.platform.appointmentManagement.infrastructure.persistence.jpa.repositories.AppointmentRepository;

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
    public Optional<Appointment> handle(GetAppointmentByIdQuery query) {
        return appointmentRepository.findById(query.appointmentId());
    }
    @Override
    public List<Appointment> handle(GetAllAppointmentsQuery query) {
        return appointmentRepository.findAll();
    }
}