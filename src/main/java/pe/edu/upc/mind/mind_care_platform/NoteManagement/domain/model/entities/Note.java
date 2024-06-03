package pe.edu.upc.mind.mind_care_platform.NoteManagement.domain.model.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import pe.edu.upc.mind.mind_care_platform.NoteManagement.domain.model.aggregates.AppointmentNote;

@Getter
@Entity
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    private String content;

    @OneToOne(mappedBy = "note", cascade = CascadeType.ALL)
    private AppointmentNote appointmentNote;

    public Note() {
        this.content = "";
    }

    public Note(String content) {
        this.content = content;
    }
}
