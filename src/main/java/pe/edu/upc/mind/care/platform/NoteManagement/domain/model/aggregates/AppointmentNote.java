package pe.edu.upc.mind.care.platform.NoteManagement.domain.model.aggregates;


import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.AbstractAggregateRoot;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import pe.edu.upc.mind.care.platform.NoteManagement.domain.model.entities.Note;
import pe.edu.upc.mind.care.platform.NoteManagement.domain.model.valueobjects.AppointmentId;
import pe.edu.upc.mind.care.platform.searchandmatch.domain.model.valueobjects.PatientId;
import pe.edu.upc.mind.care.platform.searchandmatch.domain.model.valueobjects.PsychologistId;

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

    @Embedded
    @Column(insertable = false, updatable = false)
    private AppointmentId appointmentId;

    @OneToOne(mappedBy = "appointmentNote", cascade = CascadeType.ALL)
    private Note note;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private Date createdAt;

    @LastModifiedDate
    @Column(nullable = false)
    private Date updatedAt;

    public AppointmentNote() {}

    public AppointmentNote(PsychologistId psychologistId, PatientId patientId, AppointmentId appointmentId, Note note) {
        this.psychologistId = psychologistId;
        this.patientId = patientId;
        this.appointmentId = appointmentId;
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
