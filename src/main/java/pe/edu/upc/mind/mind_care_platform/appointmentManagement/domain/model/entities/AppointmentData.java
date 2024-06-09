package pe.edu.upc.mind.mind_care_platform.appointmentManagement.domain.model.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import pe.edu.upc.mind.mind_care_platform.appointmentManagement.domain.model.aggregates.Appointment;
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

    @ManyToOne
    @JoinColumn(name = "appointment_id")
    @NotNull
    private Appointment appointment;

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

    @ManyToOne
    @JoinColumn(name = "next_item_id")
    private AppoinmentData nextItem;

    public AppoinmentData(String sessionName, LocalDateTime date, LocalDateTime time, MeetingType meetingType, PsychologistId psychologistId, PatientId patientId, AppoinmentData nextItem) {
        this.sessionName = sessionName;
        this.date = date;
        this.time = time;
        this.meetingType = meetingType;
        this.psychologistId = psychologistId;
        this.patientId = patientId;
        this.nextItem = nextItem;
    }

    public AppoinmentData(){
        this.nextItem = null;
    }

    public void updateNextItem(AppoinmentData nextItem) {
        this.nextItem = nextItem;
    }
}