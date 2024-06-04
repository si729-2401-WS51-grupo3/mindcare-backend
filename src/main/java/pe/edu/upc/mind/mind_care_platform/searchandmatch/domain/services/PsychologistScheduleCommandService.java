package pe.edu.upc.mind.mind_care_platform.searchandmatch.domain.services;

import pe.edu.upc.mind.mind_care_platform.searchandmatch.domain.model.commands.CreatePsychologistScheduleCommand;
import pe.edu.upc.mind.mind_care_platform.searchandmatch.domain.model.commands.UpdatePsychologistScheduleCommand;
import pe.edu.upc.mind.mind_care_platform.searchandmatch.domain.model.entities.PsychologistSchedule;

import java.util.Optional;

public interface PsychologistScheduleCommandService {
    Long handle(CreatePsychologistScheduleCommand command);
    Optional<PsychologistSchedule> handle(UpdatePsychologistScheduleCommand command);
}
