package pe.edu.upc.mind.mind_care_platform.searchandmatch.application.internal.queryservices;

import org.springframework.stereotype.Service;
import pe.edu.upc.mind.mind_care_platform.searchandmatch.domain.model.entities.Schedule;
import pe.edu.upc.mind.mind_care_platform.searchandmatch.domain.model.queries.*;
import pe.edu.upc.mind.mind_care_platform.searchandmatch.domain.services.ScheduleQueryService;
import pe.edu.upc.mind.mind_care_platform.searchandmatch.infrastructure.persistence.jpa.repositories.ScheduleRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ScheduleQueryServiceImpl implements ScheduleQueryService
{
    private final ScheduleRepository scheduleRepository;
    public ScheduleQueryServiceImpl(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }
    @Override
    public Optional<Schedule> handle(GetScheduleByIdQuery query) {
        return scheduleRepository.findById(query.id());
    }

    @Override
    public List<Schedule> handle(GetAllSchedulesQuery query) {
        return scheduleRepository.findAll();
    }

    @Override
    public Optional<Schedule> handle(GetScheduleByDayQuery query) {
        return scheduleRepository.findByDay(query.day());
    }

    @Override
    public List<Schedule> handle(GetScheduleByPsychologistIdAndDayQuery query) {
        return scheduleRepository.findByPsychologistIdAndDay(query.psychologistId(), query.day());
    }

    @Override
    public List<Schedule> handle(GetScheduleByPsychologistIdQuery query){
        return scheduleRepository.findByPsychologistId(query.psychologistId());
    }
}
