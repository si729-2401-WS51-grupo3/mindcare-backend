package pe.edu.upc.mind.mind_care_platform.profiles.interfaces.rest.transform;

import pe.edu.upc.mind.mind_care_platform.profiles.domain.model.aggregates.Patient;
import pe.edu.upc.mind.mind_care_platform.profiles.domain.model.entities.Profile;
import pe.edu.upc.mind.mind_care_platform.profiles.interfaces.rest.resources.PatientResource;

public class PatientResourceFromEntityAssembler {
    public static PatientResource toResourceFromEntity(Patient entity) {
    return new PatientResource(
            entity.getId(),
        entity.getFullName(),
            entity.getEmailAddress(),
            entity.getPhoneNumber(),
            entity.getPhotoUrl(),
            entity.getOccupation(),
            entity.getDescription(),
            entity.getGender(),
            entity.getBirthDate());
  }
}
