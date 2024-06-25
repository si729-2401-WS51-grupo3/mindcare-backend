package pe.edu.upc.mind.mind_care_platform.profiles.application.internal.queryservices;

import org.springframework.stereotype.Service;
import pe.edu.upc.mind.mind_care_platform.profiles.domain.model.aggregates.Psychologist;
import pe.edu.upc.mind.mind_care_platform.profiles.domain.model.entities.Profile;
import pe.edu.upc.mind.mind_care_platform.profiles.domain.model.queries.GetAllPsychologistsQuery;
import pe.edu.upc.mind.mind_care_platform.profiles.domain.model.queries.GetPsychologistByEmailQuery;
import pe.edu.upc.mind.mind_care_platform.profiles.domain.model.queries.GetPsychologistByIdQuery;
import pe.edu.upc.mind.mind_care_platform.profiles.domain.services.PsychologistQueryService;
import pe.edu.upc.mind.mind_care_platform.profiles.infrastructure.persistence.jpa.repositories.PsychologistRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PsychologistQueryServiceImpl implements PsychologistQueryService {
    private final PsychologistRepository psychologistRepository;

    public PsychologistQueryServiceImpl(PsychologistRepository pscyhologistRepository) {
        this.psychologistRepository = pscyhologistRepository;
    }

    @Override
    public Optional<Psychologist> handle(GetPsychologistByEmailQuery query){
        return psychologistRepository.findByEmail_Address(query.emailAddress().address());
    }

    @Override
    public Optional<Psychologist> handle(GetPsychologistByIdQuery query) {
        return psychologistRepository.findById(query.psychologistId());
    }

    @Override
    public List<Psychologist> handle(GetAllPsychologistsQuery query) {
        return psychologistRepository.findAll();
    }
}
