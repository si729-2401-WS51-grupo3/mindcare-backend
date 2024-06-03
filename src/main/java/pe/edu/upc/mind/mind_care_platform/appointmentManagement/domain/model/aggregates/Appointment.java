package pe.edu.upc.mind.mind_care_platform.appointmentManagement.domain.model.aggregates;

import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import pe.edu.upc.mind.mind_care_platform.appointmentManagement.domain.model.entities.AppoinmentData;
import pe.edu.upc.mind.mind_care_platform.appointmentManagement.domain.model.valueobjects.MeetingType;
import pe.edu.upc.mind.mind_care_platform.appointmentManagement.domain.model.valueobjects.PatientId;
import pe.edu.upc.mind.mind_care_platform.appointmentManagement.domain.model.valueobjects.PsychologistId;
import pe.edu.upc.mind.mind_care_platform.shared.domain.model.entities.AuditableModel;
import java.time.LocalDateTime;


/**
 * Represents an appointment.
 * The appointment is an aggregate root.
 */
@EntityListeners(AuditingEntityListener.class)
@Entity
public class Appointment extends AuditableModel {
    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "appointment_data_id", referencedColumnName = "id")
    private AppoinmentData appointmentData;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    public Appointment(AppoinmentData appointmentData) {
        this.appointmentData = appointmentData;
    }

    public Appointment() {
        this.appointmentData = new AppoinmentData();
    }

    public Appointment(String s, LocalDateTime date, LocalDateTime time, MeetingType meetingType, PsychologistId psychologistId, PatientId patientId) {
        this.appointmentData = new AppoinmentData(s, date, time, meetingType, psychologistId, patientId);
    }

    /**
     * Updates the appointment details.
     * @param sessionName The session name.
     * @param date The date of the appointment.
     * @param time The time of the appointment.
     * @param meetingType The type of the meeting.
     * @param psychologistId The id of the psychologist.
     * @param patientId The id of the patient.
     */
    public void updateAppointment(String sessionName, LocalDateTime date, LocalDateTime time, MeetingType meetingType, PsychologistId psychologistId, PatientId patientId) {
        this.appointmentData.updateMeeting(sessionName, date, time, meetingType, psychologistId, patientId);
    }
}