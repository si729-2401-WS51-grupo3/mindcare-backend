package pe.edu.upc.mind.mind_care_platform.searchandmatch.domain.services;

import pe.edu.upc.mind.mind_care_platform.searchandmatch.domain.model.aggregates.Reservation;
import pe.edu.upc.mind.mind_care_platform.searchandmatch.domain.model.queries.GetReservationsByPatientIdAndReservationDate;
import pe.edu.upc.mind.mind_care_platform.searchandmatch.domain.model.queries.GetReservationsByPatientIdQuery;

import java.util.List;

public interface ReservationQueryService {
    List<Reservation> handle(GetReservationsByPatientIdQuery query);
    List<Reservation> handle(GetReservationsByPatientIdAndReservationDate query);
}
