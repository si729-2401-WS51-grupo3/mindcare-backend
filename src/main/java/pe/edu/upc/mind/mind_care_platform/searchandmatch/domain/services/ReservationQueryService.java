package pe.edu.upc.mind.mind_care_platform.searchandmatch.domain.services;

import pe.edu.upc.mind.mind_care_platform.searchandmatch.domain.model.entities.Reservation;
import pe.edu.upc.mind.mind_care_platform.searchandmatch.domain.model.queries.GetAllPsychologistsQuery;
import pe.edu.upc.mind.mind_care_platform.searchandmatch.domain.model.queries.GetPatientIdQuery;
import pe.edu.upc.mind.mind_care_platform.searchandmatch.domain.model.queries.GetPsychologistIdQuery;

import java.util.Optional;


public interface ReservationQueryService {
    Optional<Reservation> handle(GetAllPsychologistsQuery query);
    Optional<Reservation> handle (GetPatientIdQuery query);
    Optional<Reservation> handle(GetPsychologistIdQuery query);
}
