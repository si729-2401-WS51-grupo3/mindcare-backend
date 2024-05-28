package pe.edu.upc.mind.mind_care_platform.TherapyDesign.infrastructure.persistence.jpa.repositories;

import pe.edu.upc.mind.mind_care_platform.TherapyDesign.domain.model.aggregates.AppointmentAggregate;
import pe.edu.upc.mind.mind_care_platform.TherapyDesign.domain.model.entities.Appointment;
import pe.edu.upc.mind.mind_care_platform.TherapyDesign.domain.model.entities.AppointmentNotes;
import pe.edu.upc.mind.mind_care_platform.TherapyDesign.domain.model.entities.AppointmentSummary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public class AppointmentAggregateRepository {
    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private AppointmentNotesRepository appointmentNotesRepository;

    @Autowired
    private AppointmentSummaryRepository appointmentSummaryRepository;

    public Optional<AppointmentAggregate> findById(int appointmentId) {
        Optional<Appointment> appointment = appointmentRepository.findById(appointmentId);
        if (appointment.isPresent()) {
            AppointmentAggregate aggregate = new AppointmentAggregate(appointment.get());
            Optional<AppointmentNotes> notes = appointmentNotesRepository.findById(appointmentId);
            notes.ifPresent(aggregate::setAppointmentNotes);
            Optional<AppointmentSummary> summary = appointmentSummaryRepository.findById(appointmentId);
            summary.ifPresent(aggregate::setAppointmentSummary);
            return Optional.of(aggregate);
        }
        return Optional.empty();
    }

    public void save(AppointmentAggregate aggregate) {
        appointmentRepository.save(aggregate.getAppointment());
        aggregate.getAppointmentNotes().ifPresent(appointmentNotesRepository::save);
        aggregate.getAppointmentSummary().ifPresent(appointmentSummaryRepository::save);
    }
}
