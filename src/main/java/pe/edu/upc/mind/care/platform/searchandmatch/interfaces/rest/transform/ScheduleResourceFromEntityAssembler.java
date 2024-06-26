package pe.edu.upc.mind.care.platform.searchandmatch.interfaces.rest.transform;


import pe.edu.upc.mind.care.platform.searchandmatch.domain.model.aggregates.Schedule;
import pe.edu.upc.mind.care.platform.searchandmatch.interfaces.rest.resources.ScheduleResource;

public class ScheduleResourceFromEntityAssembler {
    public static ScheduleResource toResourceFromEntity(Schedule entity) {
        return new ScheduleResource(entity.getId(), entity.getPsychologistId().psychologistId(), entity.getStartedHour(), entity.getFinishedHour());
    }
}
