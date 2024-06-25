package pe.edu.upc.mind.mind_care_platform.profiles.interfaces.rest.transform;

import pe.edu.upc.mind.mind_care_platform.profiles.domain.model.aggregates.Psychologist;
import pe.edu.upc.mind.mind_care_platform.profiles.interfaces.rest.resources.PsychologistResource;

public class PsychologistResourceFromEntityAssembler {
    public static PsychologistResource toResourceFromEntity(Psychologist psychologist) {
        return new PsychologistResource(
            psychologist.getId(),
            psychologist.getFullName(),
            psychologist.getEmailAddress(),
            psychologist.getPhoneNumber(),
            psychologist.getPhotoUrl(),
            psychologist.getOccupation(),
            psychologist.getDescription(),
            psychologist.getGender(),
            psychologist.getBirthDate(),
            psychologist.getScheduleId()
        );
    }
}
