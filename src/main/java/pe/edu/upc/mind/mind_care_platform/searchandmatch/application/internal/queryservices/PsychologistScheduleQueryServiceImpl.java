package pe.edu.upc.mind.mind_care_platform.searchandmatch.application.internal.queryservices;

import org.springframework.stereotype.Service;
import pe.edu.upc.mind.mind_care_platform.searchandmatch.domain.model.entities.PsychologistSchedule;
import pe.edu.upc.mind.mind_care_platform.searchandmatch.domain.model.queries.GetPsychologistScheduleByPsychologistIdQuery;
import pe.edu.upc.mind.mind_care_platform.searchandmatch.domain.services.PsychologistScheduleQueryService;
import pe.edu.upc.mind.mind_care_platform.searchandmatch.infrastructure.persistence.jpa.repositories.PsychologistScheduleRepository;

import java.util.Optional;

@Service
public class PsychologistScheduleQueryServiceImpl implements PsychologistScheduleQueryService {
    private final PsychologistScheduleRepository psychologistScheduleRepository;
    public PsychologistScheduleQueryServiceImpl(PsychologistScheduleRepository psychologistScheduleRepository) {
        this.psychologistScheduleRepository = psychologistScheduleRepository;
    }
    @Override
    public Optional<PsychologistSchedule> handle(GetPsychologistScheduleByPsychologistIdQuery query) {
        return psychologistScheduleRepository.findById(query.psychologistId());
    }
}
