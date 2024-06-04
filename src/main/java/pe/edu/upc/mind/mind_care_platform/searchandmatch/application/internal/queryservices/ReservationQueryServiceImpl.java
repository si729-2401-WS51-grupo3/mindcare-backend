package pe.edu.upc.mind.mind_care_platform.searchandmatch.application.internal.queryservices;

import org.springframework.stereotype.Service;
import pe.edu.upc.mind.mind_care_platform.searchandmatch.domain.model.aggregates.Reservation;
import pe.edu.upc.mind.mind_care_platform.searchandmatch.domain.model.queries.GetReservationsByPatientIdAndReservationDate;
import pe.edu.upc.mind.mind_care_platform.searchandmatch.domain.model.queries.GetReservationsByPatientIdQuery;
import pe.edu.upc.mind.mind_care_platform.searchandmatch.domain.services.ReservationQueryService;
import pe.edu.upc.mind.mind_care_platform.searchandmatch.infrastructure.persistence.jpa.repositories.ReservationRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ReservationQueryServiceImpl implements ReservationQueryService {
    private final ReservationRepository reservationRepository;
    public ReservationQueryServiceImpl(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }
    @Override
    public List<Reservation> handle(GetReservationsByPatientIdQuery query) {
        return reservationRepository.findByPatientId(query.patientId());
    }
    @Override
    public List<Reservation> handle(GetReservationsByPatientIdAndReservationDate query) {
        return reservationRepository.findByPatientIdAndReservationDate(query.patientId(), query.reservationDate());
    }
}
