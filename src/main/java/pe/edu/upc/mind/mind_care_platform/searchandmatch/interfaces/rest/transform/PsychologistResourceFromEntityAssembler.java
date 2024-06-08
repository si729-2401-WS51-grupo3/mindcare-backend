package pe.edu.upc.mind.mind_care_platform.searchandmatch.interfaces.rest.transform;

import pe.edu.upc.mind.mind_care_platform.searchandmatch.domain.model.entities.Psychologist;
import pe.edu.upc.mind.mind_care_platform.searchandmatch.interfaces.rest.resources.PsychologistResource;

public class PsychologistResourceFromEntityAssembler {
    public static PsychologistResource toResourceFromEntity(Psychologist entity) {
        return new PsychologistResource(entity.getName(), entity.getWorked_hours(), entity.getStarted_hour());
    }
}
