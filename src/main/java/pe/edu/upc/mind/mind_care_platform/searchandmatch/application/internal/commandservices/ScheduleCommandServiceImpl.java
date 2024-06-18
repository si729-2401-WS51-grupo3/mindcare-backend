package pe.edu.upc.mind.mind_care_platform.searchandmatch.application.internal.commandservices;

import org.springframework.stereotype.Service;
import pe.edu.upc.mind.mind_care_platform.searchandmatch.domain.exceptions.ReservationExeption;
import pe.edu.upc.mind.mind_care_platform.searchandmatch.domain.exceptions.PsychologistNotFoundException;
import pe.edu.upc.mind.mind_care_platform.searchandmatch.domain.exceptions.ScheduleNotFoundException;
import pe.edu.upc.mind.mind_care_platform.searchandmatch.domain.model.aggregates.Schedule;
import pe.edu.upc.mind.mind_care_platform.searchandmatch.domain.model.commands.AddReservationToScheduleCommand;
import pe.edu.upc.mind.mind_care_platform.searchandmatch.domain.model.commands.CreateScheduleCommand;
import pe.edu.upc.mind.mind_care_platform.searchandmatch.domain.model.entities.Reservation;
import pe.edu.upc.mind.mind_care_platform.searchandmatch.domain.services.ScheduleCommandService;
import pe.edu.upc.mind.mind_care_platform.searchandmatch.infrastructure.persistence.jpa.repositories.ReservationRepository;
import pe.edu.upc.mind.mind_care_platform.searchandmatch.infrastructure.persistence.jpa.repositories.ScheduleRepository;

@Service
public class ScheduleCommandServiceImpl implements ScheduleCommandService {
    private final ScheduleRepository scheduleRepository;
    private final ReservationRepository reservationRepository;

    public ScheduleCommandServiceImpl(ScheduleRepository scheduleRepository, ReservationRepository reservationRepository) {
        this.scheduleRepository = scheduleRepository;
        this.reservationRepository = reservationRepository;
    }

    @Override
    public Schedule handle(CreateScheduleCommand command) {
        if(scheduleRepository.existsByPsychologistId(command.psychologistId())){
            throw new PsychologistNotFoundException(command.psychologistId().psychologistId());
        }
        var schedule = new Schedule(command.psychologistId(),command.startedHour(),command.finishedHour());
        scheduleRepository.save(schedule);
        return schedule;
    }

    @Override
    public Schedule handle(AddReservationToScheduleCommand command) {
        if (!scheduleRepository.existsById(command.scheduleId())) {
            throw new ScheduleNotFoundException(command.scheduleId());
        }
        if (reservationRepository.existsByPatientIdPsychologistIdAndReservationDate(command.patientId().patientId(), command.psychologistId().psychologistId(), command.reservationDate())) {
            throw new ReservationExeption(command.patientId().patientId(), command.psychologistId().psychologistId(), command.reservationDate());
        }
        var schedule = scheduleRepository.findById(command.scheduleId()).get();
        var reservation = schedule.getReservations();
        reservation.add(new Reservation(schedule, command.patientId(), command.psychologistId(), command.reservationHour(),command.reservationDate()));
        scheduleRepository.save(schedule);
        return schedule;
    }
}
