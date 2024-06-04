package pe.edu.upc.mind.mind_care_platform.searchandmatch.application.internal.commandservices;

import org.springframework.stereotype.Service;
import pe.edu.upc.mind.mind_care_platform.searchandmatch.domain.model.aggregates.Reservation;
import pe.edu.upc.mind.mind_care_platform.searchandmatch.domain.model.commands.CreateReservationCommand;
import pe.edu.upc.mind.mind_care_platform.searchandmatch.domain.services.ReservationCommandService;
import pe.edu.upc.mind.mind_care_platform.searchandmatch.infrastructure.persistence.jpa.repositories.ReservationRepository;

@Service
public class ReservationCommandServiceImpl implements ReservationCommandService {
    private final ReservationRepository reservationRepository;
    public ReservationCommandServiceImpl(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @Override
    public Long handle(CreateReservationCommand command) {
        var reservation = new Reservation(
                command.reservationDate(),
                command.reservationTime(),
                command.patientId()
        ){
        };
        try {
            reservationRepository.save(reservation);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while saving reservation: " + e.getMessage());
        }
        return reservation.getId();
    }
}
