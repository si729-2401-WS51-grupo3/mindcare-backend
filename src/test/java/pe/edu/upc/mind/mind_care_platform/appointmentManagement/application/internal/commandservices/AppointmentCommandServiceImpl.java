package pe.edu.upc.mind.mind_care_platform.appointmentManagement.application.internal.commandservices;

import org.springframework.stereotype.Service;
import pe.edu.upc.mind.mind_care_platform.appointmentManagement.domain.model.aggregates.Appointment;
import pe.edu.upc.mind.mind_care_platform.appointmentManagement.domain.model.commands.CreateAppointmentCommand;
import pe.edu.upc.mind.mind_care_platform.appointmentManagement.domain.model.commands.UpdateAppointmentCommand;
import pe.edu.upc.mind.mind_care_platform.appointmentManagement.domain.model.commands.CancelAppointmentCommand;
import pe.edu.upc.mind.mind_care_platform.appointmentManagement.domain.services.AppointmentCommandService;
import pe.edu.upc.mind.mind_care_platform.appointmentManagement.infrastructure.persistence.jpa.repositories.AppointmentRepository;

import java.util.Optional;

@Service
public class AppointmentCommandServiceImpl implements AppointmentCommandService {

    private final AppointmentRepository appointmentRepository;

    public AppointmentCommandServiceImpl(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    @Override
    public Long handle(CreateAppointmentCommand command) {
        var appointment = new Appointment(command.sessionName(), command.date(), command.time(), command.meetingType(), command.psychologistId(), command.patientId());
        appointmentRepository.save(appointment);
        return appointment.getId();
    }

    @Override
    public Optional<Appointment> handle(UpdateAppointmentCommand command) {
        if (!appointmentRepository.existsById(command.appointmentId()))
            throw new IllegalArgumentException("Appointment does not exist");
        var appointmentToUpdate = appointmentRepository.findById(command.appointmentId()).get();
        appointmentToUpdate.updateAppointment(command.sessionName(), command.date(), command.time(), command.meetingType(), command.psychologistId(), command.patientId());
        var updatedAppointment = appointmentRepository.save(appointmentToUpdate);
        return Optional.of(updatedAppointment);
    }

    @Override
    public void handle(CancelAppointmentCommand command) {
        if (!appointmentRepository.existsById(command.appointmentId())) {
            throw new IllegalArgumentException("Appointment does not exist");
        }
        appointmentRepository.deleteById(command.appointmentId());
    }
}