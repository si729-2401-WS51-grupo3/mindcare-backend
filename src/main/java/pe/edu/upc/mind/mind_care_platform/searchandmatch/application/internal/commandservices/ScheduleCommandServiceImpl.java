package pe.edu.upc.mind.mind_care_platform.searchandmatch.application.internal.commandservices;

import jakarta.validation.constraints.Email;
import org.springframework.stereotype.Service;
import pe.edu.upc.mind.mind_care_platform.searchandmatch.domain.model.aggregates.Schedule;
import pe.edu.upc.mind.mind_care_platform.searchandmatch.domain.model.commands.AddReservationToScheduleCommand;
import pe.edu.upc.mind.mind_care_platform.searchandmatch.domain.model.commands.CreateScheduleCommand;
import pe.edu.upc.mind.mind_care_platform.searchandmatch.domain.model.entities.Reservation;
import pe.edu.upc.mind.mind_care_platform.searchandmatch.domain.services.ScheduleCommandService;
import pe.edu.upc.mind.mind_care_platform.searchandmatch.infrastructure.persistence.jpa.repositories.ScheduleRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ScheduleCommandServiceImpl implements ScheduleCommandService {
    ScheduleRepository scheduleRepository;
    public ScheduleCommandServiceImpl(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    @Override
    public Schedule handle(CreateScheduleCommand command) {
        if(scheduleRepository.existsByPsychologistId(command.psychologistId())){
            throw new IllegalArgumentException("Schedule for that psychologist already exists");
        }
        var schedule = new Schedule(command.psychologistId(),command.startedHour(),command.finishedHour());
        scheduleRepository.save(schedule);
        return schedule;
    }

    @Override
    public Schedule handle(AddReservationToScheduleCommand command) {
        if (!scheduleRepository.existsById(command.scheduleId())) {
            throw new IllegalArgumentException("Schedule does not exist");
        }
        var schedule = scheduleRepository.findById(command.scheduleId()).get();
        var reservation = schedule.getReservations();
        reservation.add(new Reservation(schedule, command.patientId(), command.psychologistId(), command.reservationHour(),command.reservationDate()));
        scheduleRepository.save(schedule);
        return schedule;
    }
}
