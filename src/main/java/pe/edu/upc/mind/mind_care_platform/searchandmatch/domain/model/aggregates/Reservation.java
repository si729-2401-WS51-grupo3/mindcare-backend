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
import pe.edu.upc.mind.mind_care_platform.searchandmatch.domain.model.valueobjects.PatientId;

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
    private PatientId patientId;
    public Reservation() {
    }

    public Reservation(Date reservationDate, String reservationTime, PatientId patientId) {
        this.reservationDate = reservationDate;
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

    public static Reservation createFromSchedule(PsychologistSchedule schedule, Date reservationDate, String reservationTime, PatientId patientId ) throws ParseException {
        Reservation reservation = new Reservation(reservationDate, reservationTime, patientId);
        reservation.schedule = schedule;
        // Add the reservation to the schedule
        schedule.addReservation(reservation);
        return reservation;
    }

    public Long getReservationId() {
        return this.id;
    }
    public String getReservationTime() {
        return this.reservationTime;
    }
    public String getReservationDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.format(this.reservationDate);
    }
    public Long getPatientId() {
        return this.patientId.patientId();
    }

}
