package pe.edu.upc.mind.mind_care_platform.searchandmatch.domain.services;

import pe.edu.upc.mind.mind_care_platform.searchandmatch.domain.model.entities.Schedule;
import pe.edu.upc.mind.mind_care_platform.searchandmatch.domain.model.queries.*;

import java.util.List;
import java.util.Optional;

public interface ScheduleQueryService {
    Optional<Schedule> handle(GetScheduleByIdQuery query);
    List<Schedule> handle(GetAllSchedulesQuery query);
    Optional<Schedule> handle(GetScheduleByDayQuery query);
    List<Schedule> handle(GetScheduleByPsychologistIdAndDayQuery query);
    List<Schedule> handle(GetScheduleByPsychologistIdQuery query);
}
