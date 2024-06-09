package pe.edu.upc.mind.mind_care_platform.searchandmatch.domain.services;


import pe.edu.upc.mind.mind_care_platform.searchandmatch.domain.model.commands.CreateScheduleCommand;
import pe.edu.upc.mind.mind_care_platform.searchandmatch.domain.model.commands.DeleteScheduleCommand;
import pe.edu.upc.mind.mind_care_platform.searchandmatch.domain.model.commands.UpdateScheduleCommand;
import pe.edu.upc.mind.mind_care_platform.searchandmatch.domain.model.commands.UpdateScheduleHourStatusCommand;
import pe.edu.upc.mind.mind_care_platform.searchandmatch.domain.model.entities.Schedule;

import java.util.Optional;

public interface ScheduleCommandService {
    Long handle(CreateScheduleCommand command);
    Optional<Schedule> handle(UpdateScheduleCommand command);
    void handle(DeleteScheduleCommand command);
    Optional<Schedule> handle(UpdateScheduleHourStatusCommand command);

}
