package pe.edu.upc.mind.mind_care_platform.searchandmatch.domain.model.commands;

import java.sql.Time;

public record CreateReservationCommand(Time time, String status) {
}
