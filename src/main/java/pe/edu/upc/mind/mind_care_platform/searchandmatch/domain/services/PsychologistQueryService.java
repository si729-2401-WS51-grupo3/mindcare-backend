package pe.edu.upc.mind.mind_care_platform.searchandmatch.domain.services;
import pe.edu.upc.mind.mind_care_platform.searchandmatch.domain.model.entities.Psychologist;
import pe.edu.upc.mind.mind_care_platform.searchandmatch.domain.model.queries.GetAllPsychologistsQuery;
import pe.edu.upc.mind.mind_care_platform.searchandmatch.domain.model.queries.GetPsychologistByIdQuery;

import java.util.List;
import java.util.Optional;

public interface PsychologistQueryService {
    Optional<Psychologist> handle(GetPsychologistByIdQuery query);
    List<Psychologist> handle(GetAllPsychologistsQuery query);
}