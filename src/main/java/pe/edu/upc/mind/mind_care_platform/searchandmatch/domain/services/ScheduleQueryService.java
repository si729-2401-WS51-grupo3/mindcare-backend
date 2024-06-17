package pe.edu.upc.mind.mind_care_platform.searchandmatch.domain.services;

import pe.edu.upc.mind.mind_care_platform.searchandmatch.domain.model.aggregates.Schedule;
import pe.edu.upc.mind.mind_care_platform.searchandmatch.domain.model.entities.Reservation;
import pe.edu.upc.mind.mind_care_platform.searchandmatch.domain.model.queries.GetAllReservationsQuery;
import pe.edu.upc.mind.mind_care_platform.searchandmatch.domain.model.queries.GetReservationsByScheduleIdQuery;
import pe.edu.upc.mind.mind_care_platform.searchandmatch.domain.model.queries.GetScheduleByPsychologistIdQuery;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface ScheduleQueryService {
    Optional<Schedule> handle(GetScheduleByPsychologistIdQuery query);
    Optional<List<Reservation>> handle(GetReservationsByScheduleIdQuery query);
    List<Reservation> handle(GetAllReservationsQuery query);
}
