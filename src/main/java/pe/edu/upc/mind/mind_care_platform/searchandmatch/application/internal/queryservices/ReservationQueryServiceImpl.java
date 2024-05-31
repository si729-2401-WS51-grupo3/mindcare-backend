package pe.edu.upc.mind.mind_care_platform.searchandmatch.application.internal.queryservices;

import org.springframework.stereotype.Service;
import pe.edu.upc.mind.mind_care_platform.searchandmatch.domain.model.entities.Reservation;
import pe.edu.upc.mind.mind_care_platform.searchandmatch.domain.model.queries.GetAllPsychologistsQuery;
import pe.edu.upc.mind.mind_care_platform.searchandmatch.domain.model.queries.GetPatientIdQuery;
import pe.edu.upc.mind.mind_care_platform.searchandmatch.domain.services.ReservationQueryService;

import java.util.Optional;

@Service
public class ReservationQueryServiceImpl implements ReservationQueryService {

  private ReservationR reservationRepository;

  public ReservationQueryServiceImpl(ReservationRepository reservationRepository) {
    this.reservationRepository = reservationRepository;
  }

  @Override
  public Optional<Reservation> handle(GetAllPsychologistsQuery query) {
    // Implement the logic to handle the GetAllPsychologistsQuery
    return Optional.empty();
  }

  @Override
  public Optional<Reservation> handle(GetPatientIdQuery query) {
    // Implement the logic to handle the GetPatientIdQuery
    return Optional.empty();
  }
}