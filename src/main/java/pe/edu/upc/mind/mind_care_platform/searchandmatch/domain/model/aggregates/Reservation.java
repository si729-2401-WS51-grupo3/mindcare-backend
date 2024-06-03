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

    private Long patientId;

    public Reservation() {
    }

    public Reservation(Long id, Date reservationDate, Long patientId, PsychologistSchedule schedule) throws ParseException {
        this.id = id;
        this.reservationDate = reservationDate;
        this.patientId = patientId;
        this.schedule = schedule;

    }
    public void checkReservationTime() throws ParseException {
        if (!schedule.isWithinWorkingHours(reservationDate)) {
            throw new IllegalArgumentException("The reservation time is outside the psychologist's working hours.");
        }
    }


    // summary
    //  Actualiza el horario del psicólogo después de reservar una cita.
    //  La función divide el rango de horas de trabajo en dos partes:
    //  antes de la hora de la reserva y después de la hora de la reserva.
    //  Luego, agrega las dos partes al horario del psicólogo.
    private void updateSchedule(Date reservationDate, String workingHoursRange, PsychologistSchedule schedule) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
        String[] times = workingHoursRange.split(" - ");
        Date workingHoursStart = formatter.parse(times[0]);
        Date workingHoursEnd = formatter.parse(times[1]);

        schedule.getSchedule().remove(workingHoursRange);

        if (!reservationDate.equals(workingHoursStart)) {
            String newWorkingHoursRange = formatter.format(workingHoursStart) + " - " + formatter.format(reservationDate);
            schedule.getSchedule().add(newWorkingHoursRange);
        }

        if (!reservationDate.equals(workingHoursEnd)) {
            String newWorkingHoursRange = formatter.format(reservationDate) + " - " + formatter.format(workingHoursEnd);
            schedule.getSchedule().add(newWorkingHoursRange);
        }
    }
    public void checkReservationDate(Date reservationDate) {
        Date currentDate = new Date();
        if (reservationDate.before(currentDate)) {
            throw new IllegalArgumentException("Reservation date cannot be in the past.");
        }
    }
}
