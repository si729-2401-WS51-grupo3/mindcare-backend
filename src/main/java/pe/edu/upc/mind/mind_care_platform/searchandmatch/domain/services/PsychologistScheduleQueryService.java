package pe.edu.upc.mind.mind_care_platform.searchandmatch.domain.services;

import pe.edu.upc.mind.mind_care_platform.searchandmatch.domain.model.entities.PsychologistSchedule;
import pe.edu.upc.mind.mind_care_platform.searchandmatch.domain.model.queries.GetPsychologistScheduleByPsychologistIdQuery;

import java.util.Optional;

public interface PsychologistScheduleQueryService {
    Optional<PsychologistSchedule> handle(GetPsychologistScheduleByPsychologistIdQuery query);
}
