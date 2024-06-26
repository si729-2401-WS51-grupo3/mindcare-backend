package pe.edu.upc.mind.care.platform.NoteManagement.domain.model.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import pe.edu.upc.mind.care.platform.NoteManagement.domain.model.aggregates.AppointmentNote;
import pe.edu.upc.mind.care.platform.shared.domain.model.entity.AuditableModel;

@Getter
@Entity
public class Note extends AuditableModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    private String content;

    @OneToOne
    @JoinColumn(name = "appointmentnote_id")
    private AppointmentNote appointmentNote;

    public Note() {
        this.content = "";
    }

    public Note(String content) {
        this.content = content;
    }
}
