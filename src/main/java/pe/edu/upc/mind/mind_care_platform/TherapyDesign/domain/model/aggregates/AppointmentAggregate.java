package pe.edu.upc.mind.mind_care_platform.TherapyDesign.domain.model.aggregates;

import lombok.Getter;
import lombok.Setter;
import pe.edu.upc.mind.mind_care_platform.TherapyDesign.domain.model.entities.Appointment;
import pe.edu.upc.mind.mind_care_platform.TherapyDesign.domain.model.entities.AppointmentNotes;
import pe.edu.upc.mind.mind_care_platform.TherapyDesign.domain.model.entities.AppointmentSummary;
import pe.edu.upc.mind.mind_care_platform.TherapyDesign.domain.model.valueobjects.MeetingType;
import pe.edu.upc.mind.mind_care_platform.TherapyDesign.domain.model.valueobjects.Status;
import pe.edu.upc.mind.mind_care_platform.TherapyDesign.domain.exceptions.AppointmentNotFoundException;

import java.util.Optional;

public class AppointmentAggregate {
    @Getter
    private final Appointment appointment;
    @Setter
    private AppointmentNotes appointmentNotes;
    @Setter
    private AppointmentSummary appointmentSummary;

    public AppointmentAggregate(Appointment appointment) {
        this.appointment = appointment;
    }

    public Optional<AppointmentNotes> getAppointmentNotes() {
        return Optional.ofNullable(appointmentNotes);
    }

    public Optional<AppointmentSummary> getAppointmentSummary() {
        return Optional.ofNullable(appointmentSummary);
    }

    // Métodos de negocio para manejar la lógica del agregado

    public void scheduleAppointment(String name, MeetingType meetingType, int psychologistId, int patientId, String notes, Status status) {
        appointment.setName(name);
        appointment.setMeetingTypeId(meetingType.ordinal());
        appointment.setPsychologistId(psychologistId);
        appointment.setPatientId(patientId);
        appointment.setNotes(notes);
        appointment.setStatusId(status.ordinal());
    }

    public void updateAppointmentNotes(String patientNotes) {
        if (appointmentNotes == null) {
            throw new AppointmentNotFoundException("Appointment notes not found");
        }
        appointmentNotes.setPatientNotes(patientNotes);
    }

    public void updateAppointmentSummary(String psychologistNotes) {
        if (appointmentSummary == null) {
            throw new AppointmentNotFoundException("Appointment summary not found");
        }
        appointmentSummary.setPsychologistNotes(psychologistNotes);
    }
}
