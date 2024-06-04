package pe.edu.upc.mind.mind_care_platform.searchandmatch.domain.model.aggregates;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.AbstractAggregateRoot;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import pe.edu.upc.mind.mind_care_platform.searchandmatch.domain.model.entities.PsychologistSchedule;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
    @JoinColumn(name = "schedule_id")
    private PsychologistSchedule schedule;

    @Setter
    private Date reservationDate;
    private String reservationTime;
    private Long patientId;

    public Reservation() {
    }
    public void createReservation(Date reservationDate, String reservationTime, Long patientId) {
        this.reservationDate = reservationDate;
        this.reservationTime = reservationTime;
        this.patientId = patientId;
    }

    public static Reservation createFromSchedule(PsychologistSchedule schedule, Date reservationDate, String reservationTime, Long patientId ) throws ParseException {

        Reservation reservation = new Reservation();
        reservation.createReservation(reservationDate, reservationTime, patientId);
        reservation.schedule = schedule;

        // Add the reservation to the schedule
        schedule.addReservation(reservation);

        return reservation;
    }

}
