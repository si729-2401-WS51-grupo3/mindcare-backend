package pe.edu.upc.mind.mind_care_platform.searchandmatch.domain.services;

import pe.edu.upc.mind.mind_care_platform.searchandmatch.domain.model.queries.GetAllPsychologistsQuery;
import pe.edu.upc.mind.mind_care_platform.searchandmatch.domain.model.queries.GetPsychologistBySpecialityQuery;
import pe.edu.upc.mind.mind_care_platform.searchandmatch.domain.model.valueobjects.Psychologist;

import java.util.List;

public interface SearchQueryService {
    List<Psychologist> handle(GetAllPsychologistsQuery query);
    List<Psychologist> handle(GetPsychologistBySpecialityQuery query);
}
