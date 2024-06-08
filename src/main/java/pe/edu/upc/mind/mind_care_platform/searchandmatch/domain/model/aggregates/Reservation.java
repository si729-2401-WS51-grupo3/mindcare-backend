package pe.edu.upc.mind.mind_care_platform.searchandmatch.domain.model.aggregates;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.AbstractAggregateRoot;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import pe.edu.upc.mind.mind_care_platform.searchandmatch.domain.model.entities.Psychologist;
import pe.edu.upc.mind.mind_care_platform.searchandmatch.domain.model.entities.Schedule;
import pe.edu.upc.mind.mind_care_platform.searchandmatch.domain.model.valueobjects.PatientId;

import java.util.Date;

@Getter
@EntityListeners(AuditingEntityListener.class)
@Entity
public class Reservation extends AbstractAggregateRoot<Reservation> {
    @Id
    @NotNull
    private Long id;

    @CreatedDate
    private Date createdAt;

    @LastModifiedDate
    private Date updatedAt;

    @ManyToOne
    @JoinColumn(name = "psychologist_id")
    private Psychologist psychologist;

    private PatientId patientId;

    @Getter
    @Setter
    private String reservationDay;

    @Getter
    private int reservationTime;


    public Reservation() {
    }

    public Reservation(Psychologist psychologist, PatientId patientId, String reservationDay, int reservationTime) {
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
