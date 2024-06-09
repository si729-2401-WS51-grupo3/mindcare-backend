package pe.edu.upc.mind.mind_care_platform.searchandmatch.interfaces.rest.transform;

import pe.edu.upc.mind.mind_care_platform.searchandmatch.domain.model.commands.UpdateScheduleCommand;
import pe.edu.upc.mind.mind_care_platform.searchandmatch.interfaces.rest.resources.UpdateScheduleResource;

public class UpdateScheduleCommandFromResourceAssembler {
    public static UpdateScheduleCommand toCommandFromResource(Long scheduleId, UpdateScheduleResource resource) {
        return new UpdateScheduleCommand(scheduleId, resource.worked_hours(), resource.started_hour(), resource.day());
    }
}
