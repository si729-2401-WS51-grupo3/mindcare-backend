package pe.edu.upc.mind.mind_care_platform.appointmentManagement.domain.model.aggregates;

import pe.edu.upc.mind.mind_care_platform.appointmentManagement.domain.model.valueobjects.PatientId;
import pe.edu.upc.mind.mind_care_platform.appointmentManagement.domain.model.valueobjects.Type;
import pe.edu.upc.mind.mind_care_platform.appointmentManagement.domain.model.valueobjects.PsychologistId;

import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.domain.AbstractAggregateRoot;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

@Getter
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Appointment extends AbstractAggregateRoot<Appointment> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    private Date date;

    private Type type;

    private int hour;

    @Embedded
    private PsychologistId psychologistId;

    @Embedded
    private PatientId patientId;

    public Appointment() {}

    public Appointment(String title, String description, Date date, Type type, int hour, PsychologistId psychologistId, PatientId patientId) {
        this.title = title;
        this.description = description;
        this.date = date;
        this.type = type;
        this.hour = hour;
        this.psychologistId = psychologistId;
        this.patientId = patientId;
    }
}
