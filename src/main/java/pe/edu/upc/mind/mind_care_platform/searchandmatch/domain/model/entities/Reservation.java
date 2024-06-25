package pe.edu.upc.mind.mind_care_platform.searchandmatch.domain.model.entities;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import pe.edu.upc.mind.mind_care_platform.searchandmatch.domain.model.aggregates.Schedule;
import pe.edu.upc.mind.mind_care_platform.searchandmatch.domain.model.valueobjects.PatientId;
import pe.edu.upc.mind.mind_care_platform.searchandmatch.domain.model.valueobjects.PsychologistId;
import pe.edu.upc.mind.mind_care_platform.shared.domain.model.aggregate.AuditableAbstractAggregateRoot;
import pe.edu.upc.mind.mind_care_platform.shared.domain.model.entity.AuditableModel;

import java.util.Date;

@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
@Table(name = "reservation")
@Entity
public class Reservation extends AuditableModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Embedded
    private PsychologistId psychologistId;

    @ManyToOne
    @JoinColumn(name = "schedule_id")

    private Schedule schedule;

    @Embedded
    private PatientId patientId;

    private int reservationHour;

    private Date reservationDate;



    public Reservation() {
    }

    public Reservation(Long patientId) {
        this();
        this.patientId = new PatientId(patientId);
    }
    public Reservation(PatientId patientId) {
        this();
        this.patientId = patientId;
    }
    public Reservation(Schedule schedule,PatientId patientId, PsychologistId psychologistId, int reservationHour, Date reservationDate) {
        this.schedule = schedule;
        this.patientId = patientId;
        this.psychologistId = psychologistId;
        this.reservationHour = reservationHour;
        this.reservationDate = reservationDate;
    }
}
