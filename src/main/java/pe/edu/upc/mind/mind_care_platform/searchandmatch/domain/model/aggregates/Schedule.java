package pe.edu.upc.mind.mind_care_platform.searchandmatch.domain.model.aggregates;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.AbstractAggregateRoot;
import pe.edu.upc.mind.mind_care_platform.searchandmatch.domain.model.entities.Reservation;
import pe.edu.upc.mind.mind_care_platform.searchandmatch.domain.model.valueobjects.PsychologistId;
import pe.edu.upc.mind.mind_care_platform.shared.domain.model.aggregate.AuditableAbstractAggregateRoot;
import pe.edu.upc.mind.mind_care_platform.shared.domain.model.entity.AuditableModel;

import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
@Entity
public class Schedule extends AuditableModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private PsychologistId psychologistId;

    private int startedHour;

    private int finishedHour;

    @OneToMany(mappedBy = "schedule", cascade = CascadeType.ALL,orphanRemoval = true, fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<Reservation> reservations= new ArrayList<>();

    public Schedule() {
    }

    public Schedule(PsychologistId psychologistId, int startedHour, int finishedHour) {
        this.psychologistId = psychologistId;
        this.startedHour = startedHour;
        this.finishedHour = finishedHour;
    }

    public void addReservation(Reservation reservation) {
        reservations.add(reservation);
        reservation.setSchedule(this);
    }

}
