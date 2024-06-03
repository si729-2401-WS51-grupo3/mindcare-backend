package pe.edu.upc.mind.mind_care_platform.searchandmatch.application.internal.commandservices;
import org.springframework.stereotype.Service;
import pe.edu.upc.mind.mind_care_platform.searchandmatch.infrastructure.persistence.jpa.repositories.ReservationRepository;

@Service
public class ReservationCommandServiceImpl implements ReservationCommandService {

  private final ReservationRepository reservationRepository;

  public ReservationCommandServiceImpl(ReservationRepository reservationRepository) {
    this.reservationRepository = reservationRepository;
  }

  @Override
  public Long handle(CreateReservationCommand command) {
    if (reservationRepository.existsByTimeAndStatus(command.time(), command.status())) {
      throw new IllegalArgumentException("Reservation with same time and status already exists");
    }
    var reservation = new Reservation(command);
    reservationRepository.save(reservation);
    return reservation.getId();
  }

}