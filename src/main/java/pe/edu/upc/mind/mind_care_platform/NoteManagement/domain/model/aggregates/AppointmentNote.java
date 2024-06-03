package pe.edu.upc.mind.mind_care_platform.NoteManagement.domain.model.aggregates;

import pe.edu.upc.mind.mind_care_platform.NoteManagement.domain.model.entities.Appointment;
import pe.edu.upc.mind.mind_care_platform.NoteManagement.domain.model.entities.Note;
import pe.edu.upc.mind.mind_care_platform.NoteManagement.domain.model.valueobjects.PsychologistId;
import pe.edu.upc.mind.mind_care_platform.NoteManagement.domain.model.valueobjects.PatientId;
import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.AbstractAggregateRoot;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

@Getter
@Entity
@EntityListeners(AuditingEntityListener.class)
public class AppointmentNote extends AbstractAggregateRoot<AppointmentNote> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private PsychologistId psychologistId;

    @Embedded
    private PatientId patientId;

    @OneToOne
    @JoinColumn(name = "appointment_id", referencedColumnName = "id")
    private Appointment appointment;

    @OneToOne
    @JoinColumn(name = "note_id", referencedColumnName = "id")
    private Note note;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private Date createdAt;

    @LastModifiedDate
    @Column(nullable = false)
    private Date updatedAt;

    public AppointmentNote() {}

    public AppointmentNote(PsychologistId psychologistId, PatientId patientId, Appointment appointment, Note note) {
        this.psychologistId = psychologistId;
        this.patientId = patientId;
        this.appointment = appointment;
        this.note = note;
    }

    public void updateNoteContent(String content) {
        this.note.setContent(content);
    }

    public void assignPsychologist(PsychologistId psychologistId) {
        this.psychologistId = psychologistId;
    }

    public void assignPatient(PatientId patientId) {
        this.patientId = patientId;
    }
}
