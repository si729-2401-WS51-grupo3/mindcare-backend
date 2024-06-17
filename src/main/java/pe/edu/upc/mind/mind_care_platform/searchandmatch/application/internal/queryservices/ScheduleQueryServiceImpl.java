package pe.edu.upc.mind.mind_care_platform.searchandmatch.application.internal.queryservices;

import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Service;
import pe.edu.upc.mind.mind_care_platform.searchandmatch.domain.model.aggregates.Schedule;
import pe.edu.upc.mind.mind_care_platform.searchandmatch.domain.model.entities.Reservation;
import pe.edu.upc.mind.mind_care_platform.searchandmatch.domain.model.queries.GetAllReservationsQuery;
import pe.edu.upc.mind.mind_care_platform.searchandmatch.domain.model.queries.GetReservationsByScheduleIdQuery;
import pe.edu.upc.mind.mind_care_platform.searchandmatch.domain.model.queries.GetScheduleByPsychologistIdQuery;
import pe.edu.upc.mind.mind_care_platform.searchandmatch.domain.services.ScheduleQueryService;
import pe.edu.upc.mind.mind_care_platform.searchandmatch.infrastructure.persistence.jpa.repositories.ScheduleRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ScheduleQueryServiceImpl implements ScheduleQueryService {
    private final ScheduleRepository scheduleRepository;
    private final StringHttpMessageConverter stringHttpMessageConverter;

    public ScheduleQueryServiceImpl(ScheduleRepository scheduleRepository, StringHttpMessageConverter stringHttpMessageConverter) {
        this.scheduleRepository = scheduleRepository;
        this.stringHttpMessageConverter = stringHttpMessageConverter;
    }

    @Override
    public Optional<Schedule> handle(GetScheduleByPsychologistIdQuery query) {
        return scheduleRepository.findByPsychologistId(query.psychologistId());
    }

    @Override
    public Optional<List<Reservation>> handle(GetReservationsByScheduleIdQuery query) {
        return scheduleRepository.findById(query.scheduleId())
                .map(schedule -> schedule.getReservations());
    }

    @Override
    public List<Reservation> handle(GetAllReservationsQuery query) {
        return scheduleRepository.findAll().stream()
                .flatMap(schedule -> schedule.getReservations().stream())
                .collect(Collectors.toList());
    }
}
