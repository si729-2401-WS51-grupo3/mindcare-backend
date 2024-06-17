package pe.edu.upc.mind.mind_care_platform.searchandmatch.interfaces.rest.transform;

import pe.edu.upc.mind.mind_care_platform.searchandmatch.domain.model.commands.CreateScheduleCommand;
import pe.edu.upc.mind.mind_care_platform.searchandmatch.domain.model.valueobjects.PsychologistId;
import pe.edu.upc.mind.mind_care_platform.searchandmatch.interfaces.rest.resources.CreateScheduleResource;

public class CreateScheduleCommandFromResourceAssembler {
    public static CreateScheduleCommand toCommandFromResource(CreateScheduleResource resource) {
        return new CreateScheduleCommand(new PsychologistId(resource.psychologistId()), resource.startedHour(), resource.finishedHour());
    }
}
