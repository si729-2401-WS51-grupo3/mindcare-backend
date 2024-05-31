package pe.edu.upc.mind.mind_care_platform.appointmentManagement.domain.model.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import pe.edu.upc.mind.mind_care_platform.appointmentManagement.domain.model.valueobjects.MeetingType;
import pe.edu.upc.mind.mind_care_platform.appointmentManagement.domain.model.valueobjects.PatientId;
import pe.edu.upc.mind.mind_care_platform.appointmentManagement.domain.model.valueobjects.PsychologistId;
import pe.edu.upc.mind.mind_care_platform.shared.domain.model.entities.AuditableModel;

import java.time.LocalDateTime;

/**
 * Represents a meeting.
 */
@Getter
@Entity
public class AppoinmentData extends AuditableModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String sessionName;

    @NotNull
    private LocalDateTime date;

    @NotNull
    private LocalDateTime time;

    @Enumerated(EnumType.STRING)
    @NotNull
    private MeetingType meetingType;

    @Embedded
    @NotNull
    private PsychologistId psychologistId;

    @Embedded
    @NotNull
    private PatientId patientId;

    public AppoinmentData(String sessionName, LocalDateTime date, LocalDateTime time, MeetingType meetingType, PsychologistId psychologistId, PatientId patientId) {
        this.sessionName = sessionName;
        this.date = date;
        this.time = time;
        this.meetingType = meetingType;
        this.psychologistId = psychologistId;
        this.patientId = patientId;
    }

    public AppoinmentData() {
        this.sessionName = "";
        this.date = LocalDateTime.now();
        this.time = LocalDateTime.now();
        this.meetingType = MeetingType.FIRST_SESSION;
        this.psychologistId = new PsychologistId();
        this.patientId = new PatientId();
    }

    /**
     * Updates the meeting details.
     * @param sessionName The session name.
     * @param date The date of the meeting.
     * @param time The time of the meeting.
     * @param meetingType The type of the meeting.
     * @param psychologistId The id of the psychologist.
     * @param patientId The id of the patient.
     */
    public void updateMeeting(String sessionName, LocalDateTime date, LocalDateTime time, MeetingType meetingType, PsychologistId psychologistId, PatientId patientId) {
        this.sessionName = sessionName;
        this.date = date;
        this.time = time;
        this.meetingType = meetingType;
        this.psychologistId = psychologistId;
        this.patientId = patientId;
    }
}