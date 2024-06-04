package pe.edu.upc.mind.mind_care_platform.searchandmatch.domain.model.entities;

import jakarta.persistence.*;
import lombok.Getter;
import pe.edu.upc.mind.mind_care_platform.searchandmatch.domain.model.aggregates.Reservation;
import pe.edu.upc.mind.mind_care_platform.shared.domain.model.entity.AuditableModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Getter
@Entity
public class PsychologistSchedule extends AuditableModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long psychologistId;
    private int worked_hours;
    private int started_hour;
    //summary
    //se utiliza para denotar una colección de elementos básicos (propio de JPA)
    //que son instancias de un tipo de valor básico o embeddable.
    @ElementCollection
    private List<String> schedule = new ArrayList<>();
    //summary
    //La relación entre PsychologistSchedule y Reservation es de uno a muchos.
    //PsychologistSchedule puede tener muchas Reservation y
    //una Reservation está asociada a un PsychologistSchedule
    @OneToMany(mappedBy = "schedule")
    private List<Reservation> reservations;

    public PsychologistSchedule() {
    }


    public PsychologistSchedule(int worked_hours, int started_hour) {
        this.worked_hours = worked_hours;
        this.started_hour = started_hour;
        createSchedule();
    }

    //summary
    //summary
    //creando el horario del psicologo
    private void createSchedule() {
        for (int i = 0; i < worked_hours; i++) {
            int hour = started_hour + i;
            schedule.add(String.format("%02d:00 - %02d:00", hour, hour));
        }
    }
    public boolean isReservationTimeReserved(String reservationTime) {
        for (Reservation reservation : reservations) {
            if (reservation.getReservationTime().equals(reservationTime)) {
                return true;
            }
        }
        return false;
    }

    public void addReservation(Reservation reservation) {
        if (!isReservationTimeReserved(reservation.getReservationTime())) {
            reservations.add(reservation);
        } else {
            throw new IllegalArgumentException("This time  is already reserved.");
        }
    }
}
