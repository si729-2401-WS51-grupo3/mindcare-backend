package pe.edu.upc.mind.mind_care_platform.searchandmatch.domain.services;

import pe.edu.upc.mind.mind_care_platform.searchandmatch.domain.model.aggregates.Reservation;
import pe.edu.upc.mind.mind_care_platform.searchandmatch.domain.model.queries.GetAllReservationsQuery;
import pe.edu.upc.mind.mind_care_platform.searchandmatch.domain.model.queries.GetReservationByIdQuery;
import pe.edu.upc.mind.mind_care_platform.searchandmatch.domain.model.queries.GetReservationsByPatientIdAndReservationDate;
import pe.edu.upc.mind.mind_care_platform.searchandmatch.domain.model.queries.GetReservationsByPatientIdQuery;

import java.util.List;
import java.util.Optional;

public interface ReservationQueryService {
    Optional<Reservation> handle(GetReservationByIdQuery query);
    List<Reservation> handle(GetAllReservationsQuery query);
    List<Reservation> handle(GetReservationsByPatientIdQuery query);
    List<Reservation> handle(GetReservationsByPatientIdAndReservationDate query);
}
