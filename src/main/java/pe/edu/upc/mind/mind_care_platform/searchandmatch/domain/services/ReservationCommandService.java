package pe.edu.upc.mind.mind_care_platform.searchandmatch.domain.services;


import pe.edu.upc.mind.mind_care_platform.searchandmatch.domain.model.commands.CreateReservationCommand;

public interface ReservationCommandService {
   Long handle(CreateReservationCommand command);
}
