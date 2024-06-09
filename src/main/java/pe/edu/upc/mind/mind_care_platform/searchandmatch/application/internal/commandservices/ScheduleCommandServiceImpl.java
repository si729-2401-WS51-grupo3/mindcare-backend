package pe.edu.upc.mind.mind_care_platform.searchandmatch.application.internal.commandservices;

import org.springframework.stereotype.Service;
import pe.edu.upc.mind.mind_care_platform.searchandmatch.domain.model.commands.CreateScheduleCommand;
import pe.edu.upc.mind.mind_care_platform.searchandmatch.domain.model.commands.DeleteScheduleCommand;
import pe.edu.upc.mind.mind_care_platform.searchandmatch.domain.model.commands.UpdateScheduleCommand;
import pe.edu.upc.mind.mind_care_platform.searchandmatch.domain.model.commands.UpdateScheduleHourStatusCommand;
import pe.edu.upc.mind.mind_care_platform.searchandmatch.domain.model.entities.Schedule;
import pe.edu.upc.mind.mind_care_platform.searchandmatch.domain.services.ScheduleCommandService;
import pe.edu.upc.mind.mind_care_platform.searchandmatch.infrastructure.persistence.jpa.repositories.ScheduleRepository;

import java.util.Optional;

@Service
public class ScheduleCommandServiceImpl implements ScheduleCommandService {
    ScheduleRepository scheduleRepository;
    public ScheduleCommandServiceImpl(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    @Override
    public Long handle(CreateScheduleCommand command) {
        var schedule=new Schedule(command);
        try{
            scheduleRepository.save(schedule);
        }
        catch (Exception e){
            throw new RuntimeException("Error al guardar el horario " + e.getMessage());
        }
        return schedule.getId();
    }

    @Override
    public Optional<Schedule> handle(UpdateScheduleCommand command) {
        var result = scheduleRepository.findById(command.id());
        if (result.isEmpty())
            throw new IllegalArgumentException("El horario no existe");

        var scheduleToUpdate = result.get();
        try {
            var updatedSchedule = scheduleRepository.save(scheduleToUpdate.updateSchedule(command.worked_hours(), command.started_hour(), command.day()));
            return Optional.of(updatedSchedule);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error al actualizar el horario: " + e.getMessage());
        }
    }

    @Override
    public void handle(DeleteScheduleCommand command) {
        if (!scheduleRepository.existsById(command.id())) {
            throw new IllegalArgumentException("El horario no existe");
        }
        try {
            scheduleRepository.deleteById(command.id());
        } catch (Exception e) {
            throw new IllegalArgumentException("Error al eliminar el horario: " + e.getMessage());
        }
    }

    @Override
    public Optional<Schedule> handle(UpdateScheduleHourStatusCommand command) {
        var result = scheduleRepository.findById(command.id());
        if (result.isEmpty())
            throw new IllegalArgumentException("El horario no existe");

        var scheduleToUpdate = result.get();
        var hourToUpdate = scheduleToUpdate.getWorkingHours().stream()
                .filter(hour -> hour.getHour() == command.hour())
                .findFirst();

        if (hourToUpdate.isEmpty())
            throw new IllegalArgumentException("La hora no existe");

        hourToUpdate.get().setStatus(command.status());

        try {
            var updatedSchedule = scheduleRepository.save(scheduleToUpdate);
            return Optional.of(updatedSchedule);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error al actualizar el horario: " + e.getMessage());
        }
    }
}
