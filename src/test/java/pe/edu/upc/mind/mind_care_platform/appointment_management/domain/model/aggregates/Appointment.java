package pe.edu.upc.mind.mind_care_platform.appointment_management.domain.model.aggregates;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private LocalDate date;
    private LocalDateTime hour;
    private String notes;

    @ManyToOne
    @JoinColumn(name = "ID_MeetingType")
    private MeetingType meetingType;

    @ManyToOne
    @JoinColumn(name = "ID_Psychologist")
    private Psychologist psychologist;

    @ManyToOne
    @JoinColumn(name = "ID_Patient")
    private Patient patient;

    public void setPatient(Patient patient) {
    }

    public void setPsychologist(Psychologist psychologist) {
    }

    public void setMeetingType(MeetingType meetingType) {
    }

    // getters and setters
}

