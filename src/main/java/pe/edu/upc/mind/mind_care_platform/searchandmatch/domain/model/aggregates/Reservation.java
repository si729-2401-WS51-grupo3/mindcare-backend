package pe.edu.upc.mind.mind_care_platform.searchandmatch.domain.model.aggregates;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import pe.edu.upc.mind.mind_care_platform.searchandmatch.domain.model.entities.Schedule;
import pe.edu.upc.mind.mind_care_platform.searchandmatch.domain.model.valueobjects.PatientId;
import pe.edu.upc.mind.mind_care_platform.searchandmatch.domain.model.valueobjects.PsychologistId;
import pe.edu.upc.mind.mind_care_platform.shared.domain.model.aggregate.AuditableAbstractAggregateRoot;

import java.time.LocalDateTime;

@Getter
@EntityListeners(AuditingEntityListener.class)
@Entity
public class Reservation extends AuditableAbstractAggregateRoot<Reservation> {
    @Id
    @NotNull
    private Long id;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    @Embedded
    private PsychologistId psychologistId;

    @ManyToOne
    @JoinColumn(name = "schedule_id")
    private Schedule schedule;

    private PatientId patientId;

    @Getter
    @Setter
    private String reservationDay;

    @Getter
    private int reservationTime;


    public Reservation() {
    }

    public Reservation(PsychologistId psychologistId, PatientId patientId, String reservationDay, int reservationTime) {
        this.psychologistId = psychologistId;
        this.reservationDay = reservationDay;
        this.reservationTime = reservationTime;
        this.patientId = patientId;
    }

    public Reservation(Long patientId) {
        this();
        this.patientId = new PatientId(patientId);
    }
    public Reservation(PatientId patientId) {
        this();
        this.patientId = patientId;
    }

    public Long getReservationId() {
        return this.id;
    }

}
