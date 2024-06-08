package pe.edu.upc.mind.mind_care_platform.searchandmatch.application.internal.queryservices;
import org.springframework.stereotype.Service;
import pe.edu.upc.mind.mind_care_platform.searchandmatch.domain.model.entities.Psychologist;
import pe.edu.upc.mind.mind_care_platform.searchandmatch.domain.model.queries.GetAllPsychologistsQuery;
import pe.edu.upc.mind.mind_care_platform.searchandmatch.domain.model.queries.GetPsychologistByIdQuery;
import pe.edu.upc.mind.mind_care_platform.searchandmatch.domain.services.PsychologistQueryService;
import pe.edu.upc.mind.mind_care_platform.searchandmatch.infrastructure.persistence.jpa.repositories.PsychologistRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PsychologistQueryServiceImpl implements PsychologistQueryService {
    private final PsychologistRepository psychologistRepository;
    public PsychologistQueryServiceImpl (PsychologistRepository psychologistRepository) {
        this.psychologistRepository = psychologistRepository;
    }
    @Override
    public Optional<Psychologist> handle(GetPsychologistByIdQuery query) {
        return psychologistRepository.findById(query.Id());
    }
    @Override
    public List<Psychologist> handle(GetAllPsychologistsQuery query) {
        return psychologistRepository.findAll();
    }
}
