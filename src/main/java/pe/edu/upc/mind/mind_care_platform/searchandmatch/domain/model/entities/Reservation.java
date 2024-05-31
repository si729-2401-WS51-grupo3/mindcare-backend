package pe.edu.upc.mind.mind_care_platform.searchandmatch.domain.model.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import pe.edu.upc.mind.mind_care_platform.searchandmatch.domain.model.commands.CreateReservationCommand;

import java.sql.Time;

@Getter
@Setter
@Entity
public class Reservation {
    private Time time;
    @Setter
    private String status;
    @Setter
    @Id
    private Long id;

    public Reservation() {
    }
    public Reservation(CreateReservationCommand command) {
        this.time = command.time();
        this.status = command.status();
    }
}
