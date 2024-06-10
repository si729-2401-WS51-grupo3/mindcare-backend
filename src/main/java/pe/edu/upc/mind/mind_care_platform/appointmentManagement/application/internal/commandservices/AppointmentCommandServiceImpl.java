package pe.edu.upc.mind.mind_care_platform.appointmentManagement.application.internal.commandservices;

import org.springframework.stereotype.Service;
import pe.edu.upc.mind.mind_care_platform.appointmentManagement.domain.model.aggregates.Appointment;
import pe.edu.upc.mind.mind_care_platform.appointmentManagement.domain.model.commands.CreateAppointmentCommand;
import pe.edu.upc.mind.mind_care_platform.appointmentManagement.domain.model.commands.AddAppointmentDataToAppointmentCommand;
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
        if (appointmentRepository.existsBySessionName(command.sessionName())) {
            throw new IllegalArgumentException("Appointment with same title already exists");
        }
        var appointment = new Appointment(command);
        try {
            appointmentRepository.save(appointment);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while saving appointment: " + e.getMessage());
        }
        return appointment.getId();
    }

    @Override
    public void handle(AddAppointmentDataToAppointmentCommand command) {
        if (!appointmentRepository.existsById(command.appointmentDataId())) {
            throw new IllegalArgumentException("Appointment does not exist");
        }
        try {
            appointmentRepository.findById(command.appointmentDataId()).map(appointment -> {
                appointment.addAppointmentDataToAppointment(command.appointmentDataId());
                appointmentRepository.save(appointment);
                System.out.println("Appointment data added to appointment");
                return appointment;
            });
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while adding appointment data to appointment: " + e.getMessage());
        }
    }
}