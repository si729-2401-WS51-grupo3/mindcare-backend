package pe.edu.upc.mind.mind_care_platform.searchandmatch.domain.model.entities;

import jakarta.persistence.*;
import lombok.Getter;
import pe.edu.upc.mind.mind_care_platform.searchandmatch.domain.model.aggregates.Reservation;
import pe.edu.upc.mind.mind_care_platform.shared.domain.model.entity.AuditableModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


@Table(name = "psychologist_schedules")
@Getter
@Entity
public class PsychologistSchedule extends AuditableModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int worked_hours;

    private int started_hour;
    @ElementCollection
    private List<String> schedule = new ArrayList<>();


    private Long psychologistId;

    @OneToMany(mappedBy = "schedule")
    private List<Reservation> reservations;

    public PsychologistSchedule(Long psychologistId, int worked_hours, int started_hour) {
        this.psychologistId = psychologistId;
        this.worked_hours = worked_hours;
        this.started_hour = started_hour;
        generateSchedule();
    }

    public PsychologistSchedule() {
    }

    private void generateSchedule() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, started_hour);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");

        for (int i = 0; i < worked_hours; i++) {
            String startTime = formatter.format(calendar.getTime());

            calendar.add(Calendar.HOUR_OF_DAY, 1);
            String endTime = formatter.format(calendar.getTime());

            this.schedule.add(startTime + " - " + endTime);
        }
    }

    // summary
    //  Si la hora de la reserva es después de workingHoursStart
    //  y antes de workingHoursEnd, la función devuelve true,
    //  lo que significa que la hora de la reserva está dentro del rango de
    //  horas de trabajo del psicólogo. Si no, la función devuelve false.
    public boolean isWithinWorkingHours(Date reservationDate) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");

        for (String workingHoursRange : this.schedule) {
            String[] times = workingHoursRange.split(" - ");
            Date workingHoursStart = formatter.parse(times[0]);
            Date workingHoursEnd = formatter.parse(times[1]);

            if (reservationDate.after(workingHoursStart) && reservationDate.before(workingHoursEnd)) {
                return true;
            }
        }

        return false;
    }

    // summary
    //  comprueba si la hora de la reserva ya está reservada por otro paciente.
    //  Devuelve true si la hora de la reserva no está reservada y false si ya está reservada.
    public boolean isReservationTimeAvailable(Date reservationDate) throws ParseException {
        for (Reservation reservation : this.reservations) {
            if (reservation.getReservationDate().equals(reservationDate)) {
                return false;
            }
        }
        return true;
    }

    // summary
    //  comprueba si el horario del psicólogo está lleno.
    //  Devuelve true si el horario del psicólogo está lleno y false si no lo está.
    public boolean isScheduleFull() {
        for (String workingHoursRange : this.schedule) {
            SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
            String[] times = workingHoursRange.split(" - ");
            Date workingHoursStart;
            Date workingHoursEnd;
            try {
                workingHoursStart = formatter.parse(times[0]);
                workingHoursEnd = formatter.parse(times[1]);
            } catch (ParseException e) {
                throw new RuntimeException("Error parsing schedule times", e);
            }

            for (Reservation reservation : this.reservations) {
                if (reservation.getReservationDate().after(workingHoursStart) && reservation.getReservationDate().before(workingHoursEnd)) {
                    return true;
                }
            }
        }
        return false;
    }
}
