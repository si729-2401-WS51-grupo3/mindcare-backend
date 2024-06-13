package pe.edu.upc.mind.mind_care_platform.appointmentManagement.application.internal.commandservices;

import pe.edu.upc.mind.mind_care_platform.appointmentManagement.domain.model.aggregates.Appointment;
import pe.edu.upc.mind.mind_care_platform.appointmentManagement.domain.model.commands.*;
import pe.edu.upc.mind.mind_care_platform.appointmentManagement.domain.model.entities.AppointmentDetail;
import pe.edu.upc.mind.mind_care_platform.appointmentManagement.domain.services.AppointmentCommandService;
import pe.edu.upc.mind.mind_care_platform.appointmentManagement.infrastructure.persistence.jpa.repositories.AppointmentRepository;

import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class AppointmentCommandServiceImpl implements AppointmentCommandService {
    private final AppointmentRepository appointmentRepository;
    public AppointmentCommandServiceImpl(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }
    @Override
    public Appointment handle(CreateAppointmentCommand command) {
        var appointment = new Appointment(command.title(), command.type(), command.psychologistId());
        appointmentRepository.save(appointment);
        return appointment;
    }
    @Override
    public Optional<Appointment> handle(UpdateAppointmentCommand command) {
        if (!appointmentRepository.existsById(command.appointmentId())) {
            throw new IllegalArgumentException("Appointment does not exist");
        }
        var appointment = appointmentRepository.findById(command.appointmentId()).get();
        appointment.updateTitle(command.title());
        appointment.updateType(command.type());
        appointment.updatePsychologistId(command.psychologistId());
        appointmentRepository.save(appointment);
        return Optional.of(appointment);
    }
    @Override
    public void handle(DeleteAppointmentCommand command) {
        if (!appointmentRepository.existsById(command.appointmentId())) {
            throw new IllegalArgumentException("Appointment does not exist");
        }
        appointmentRepository.deleteById(command.appointmentId());
    }
    @Override
    public Appointment handle(AddAppointmentDetailToAppointmentCommand command) {
        if (!appointmentRepository.existsById(command.appointmentId())) {
            throw new IllegalArgumentException("Appointment does not exist");
        }
        var appointment = appointmentRepository.findById(command.appointmentId()).get();
        var appointmentDetail = appointment.getAppointmentDetails();
        appointmentDetail.add(new AppointmentDetail(appointment, command.description()));
        appointmentRepository.save(appointment);
        return appointment;
    }
    @Override
    public void handle(AssignPsychologistToAppointmentCommand command) {
        if (!appointmentRepository.existsById(command.appointmentId())) {
            throw new IllegalArgumentException("Appointment does not exist");
        }
        var appointment = appointmentRepository.findById(command.appointmentId()).get();
        appointment.updatePsychologistId(command.psychologistId());
        appointmentRepository.save(appointment);
    }
}
