package pe.edu.upc.mind.mind_care_platform.searchandmatch.application.internal.queryservices;

import org.springframework.stereotype.Service;
import pe.edu.upc.mind.mind_care_platform.searchandmatch.domain.model.aggregates.Reservation;
import pe.edu.upc.mind.mind_care_platform.searchandmatch.domain.model.queries.GetAllReservationsQuery;
import pe.edu.upc.mind.mind_care_platform.searchandmatch.domain.model.queries.GetReservationByIdQuery;
import pe.edu.upc.mind.mind_care_platform.searchandmatch.domain.model.queries.GetReservationsByPatientIdAndReservationDate;
import pe.edu.upc.mind.mind_care_platform.searchandmatch.domain.model.queries.GetReservationsByPatientIdQuery;
import pe.edu.upc.mind.mind_care_platform.searchandmatch.domain.services.ReservationQueryService;
import pe.edu.upc.mind.mind_care_platform.searchandmatch.infrastructure.persistence.jpa.repositories.ReservationRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationQueryServiceImpl implements ReservationQueryService {
    private final ReservationRepository reservationRepository;
    public ReservationQueryServiceImpl(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }
    @Override
    public Optional<Reservation> handle(GetReservationByIdQuery query) {
        return reservationRepository.findById(query.reservationId());
    }
    @Override
    public List<Reservation> handle(GetReservationsByPatientIdQuery query) {
        return reservationRepository.findByPatientId(query.patientId());
    }
    @Override
    public List<Reservation> handle(GetReservationsByPatientIdAndReservationDate query) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        formatter.setLenient(false);
        try {
            formatter.parse(query.reservationDate());
            return reservationRepository.findByPatientIdAndReservationDate(query.patientId(), query.reservationDate());
        } catch (ParseException e) {
            throw new IllegalArgumentException("Invalid date format. Expected format is dd-MM-yyyy.");
        }
    }
    @Override
    public List<Reservation> handle(GetAllReservationsQuery query) {
        return reservationRepository.findAll();
    }
}
