package pe.edu.upc.mind.care.platform.appointmentManagement.application.internal.commandservices;

import org.springframework.stereotype.Service;
import pe.edu.upc.mind.care.platform.appointmentManagement.domain.model.aggregates.Appointment;
import pe.edu.upc.mind.care.platform.appointmentManagement.domain.model.commands.CreateAppointmentCommand;
import pe.edu.upc.mind.care.platform.appointmentManagement.domain.services.AppointmentCommandService;
import pe.edu.upc.mind.care.platform.appointmentManagement.infrastructure.persistence.jpa.repositories.AppointmentRepository;

@Service
public class AppointmentCommandServiceImpl implements AppointmentCommandService {

    private final AppointmentRepository appointmentRepository;

    public AppointmentCommandServiceImpl(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    @Override
    public Long handle(CreateAppointmentCommand command) {
        if (appointmentRepository.existsByDateAndPatientIdAndPsychologistId(command.date(), command.patientId(), command.psychologistId())
                && appointmentRepository.existsByTitle(command.title())) {
            throw new IllegalArgumentException("Appointment with same title, date, patient and psychologist already exists");
        }
        var appointment = new Appointment();
        try {
            appointmentRepository.save(appointment);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while saving appointment: " + e.getMessage());
        }
        return appointment.getId();
    }
}
