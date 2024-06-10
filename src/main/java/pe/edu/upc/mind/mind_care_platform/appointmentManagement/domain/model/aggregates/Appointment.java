package pe.edu.upc.mind.mind_care_platform.appointmentManagement.domain.model.aggregates;

import jakarta.persistence.*;
import lombok.Getter;
import pe.edu.upc.mind.mind_care_platform.appointmentManagement.domain.model.commands.CreateAppointmentCommand;
import pe.edu.upc.mind.mind_care_platform.appointmentManagement.domain.model.valueobjects.AppointmentDataPath;
import pe.edu.upc.mind.mind_care_platform.appointmentManagement.domain.model.valueobjects.PatientId;
import pe.edu.upc.mind.mind_care_platform.appointmentManagement.domain.model.valueobjects.PsychologistId;
import pe.edu.upc.mind.mind_care_platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;


/**
 * Represents an appointment.
 * The appointment is an aggregate root.
 */
@Getter
@Entity
public class Appointment extends AuditableAbstractAggregateRoot<Appointment> {

    private String sessionName;

    @Embedded
    private final PsychologistId psychologistId;

    @Embedded
    private final PatientId patientId;

    @Embedded
    private final AppointmentDataPath appointmentDataPath;

    public Appointment() {
        this.sessionName = "";
        this.psychologistId = new PsychologistId();
        this.patientId = new PatientId();
        this.appointmentDataPath = new AppointmentDataPath();
    }
    public Appointment(String sessionName) {
        this();
        this.sessionName = sessionName;
    }
    public Appointment(CreateAppointmentCommand command) {
        this();
        this.sessionName = command.sessionName();
    }
    public void addAppointmentDataToAppointment(Long appointmentDataId) {
        System.out.println("Adding appointment data to appointment");
        this.appointmentDataPath.addItem(this, appointmentDataId);
    }
}