package pe.edu.upc.mind.mind_care_platform.searchandmatch.application.internal.commandservices;

import org.springframework.stereotype.Service;
import pe.edu.upc.mind.mind_care_platform.searchandmatch.domain.model.commands.CreatePsychologistScheduleCommand;
import pe.edu.upc.mind.mind_care_platform.searchandmatch.domain.model.commands.UpdatePsychologistScheduleCommand;
import pe.edu.upc.mind.mind_care_platform.searchandmatch.domain.model.entities.PsychologistSchedule;
import pe.edu.upc.mind.mind_care_platform.searchandmatch.domain.services.PsychologistScheduleCommandService;
import pe.edu.upc.mind.mind_care_platform.searchandmatch.infrastructure.persistence.jpa.repositories.PsychologistScheduleRepository;

import java.util.Optional;

@Service
public class PsychologistScheduleCommandServiceImpl implements PsychologistScheduleCommandService {
    private final PsychologistScheduleRepository psychologistScheduleRepository;
    public PsychologistScheduleCommandServiceImpl(PsychologistScheduleRepository psychologistScheduleRepository) {
        this.psychologistScheduleRepository = psychologistScheduleRepository;
    }

    @Override
    public Long handle(CreatePsychologistScheduleCommand command) {
        var psychologistSchedule = new PsychologistSchedule(
                command.psychologistId(),
                command.worked_hours(),
                command.started_hour()
        ){
        };
        try {
            psychologistScheduleRepository.save(psychologistSchedule);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while saving psychologist schedule: " + e.getMessage());
        }
        return psychologistSchedule.getId();
    }
    @Override
    public Optional<PsychologistSchedule> handle(UpdatePsychologistScheduleCommand command) {
        Optional<PsychologistSchedule> psychologistSchedule = psychologistScheduleRepository.findById(command.psychologistId().psychologistId());
        if (psychologistSchedule.isPresent()) {
            PsychologistSchedule schedule = psychologistSchedule.get();
            schedule.setWorked_hours(command.worked_hours());
            schedule.setStarted_hour(command.started_hour());
            psychologistScheduleRepository.save(schedule);
        }
        return psychologistSchedule;
    }
}
