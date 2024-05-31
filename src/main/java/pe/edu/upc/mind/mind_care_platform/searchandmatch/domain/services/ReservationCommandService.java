package pe.edu.upc.mind.mind_care_platform.searchandmatch.domain.services;


import pe.edu.upc.mind.mind_care_platform.searchandmatch.domain.model.commands.CreateReservationCommand;
import pe.edu.upc.mind.mind_care_platform.searchandmatch.domain.model.entities.Reservation;

import java.util.Optional;

public interface ReservationCommandService {
   Optional<Reservation>handle(CreateReservationCommand command);
}
