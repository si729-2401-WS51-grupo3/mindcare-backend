package pe.edu.upc.mind.mind_care_platform.profiles.domain.services;

import org.springframework.stereotype.Service;
import pe.edu.upc.mind.mind_care_platform.profiles.domain.model.aggregates.Psychologist;
import pe.edu.upc.mind.mind_care_platform.profiles.domain.model.entities.Profile;
import pe.edu.upc.mind.mind_care_platform.profiles.domain.model.queries.GetAllPsychologistsQuery;
import pe.edu.upc.mind.mind_care_platform.profiles.domain.model.queries.GetPsychologistByEmailQuery;
import pe.edu.upc.mind.mind_care_platform.profiles.domain.model.queries.GetPsychologistByIdQuery;

import java.util.List;
import java.util.Optional;

public interface PsychologistQueryService {
    Optional<Psychologist> handle(GetPsychologistByEmailQuery query);
    Optional<Psychologist> handle(GetPsychologistByIdQuery query);
    List<Psychologist> handle(GetAllPsychologistsQuery query);

}
