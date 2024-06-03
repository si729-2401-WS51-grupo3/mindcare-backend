package pe.edu.upc.mind.mind_care_platform.NoteManagement.interfaces.rest.transform;

import pe.edu.upc.mind.mind_care_platform.NoteManagement.domain.model.aggregates.AppointmentNote;
import pe.edu.upc.mind.mind_care_platform.NoteManagement.interfaces.rest.resources.AppointmentNoteResource;


public class AppointmentNoteResourceFromEntityAssembler {
    public static AppointmentNoteResource toResourceFromEntity(AppointmentNote entity) {
        return new AppointmentNoteResource(
                entity.getId(),
                entity.getNote().getContent(),
                entity.getAppointment().getId(),
                entity.getPsychologistId().id(),
                entity.getPatientId().id()
        );
    }
}
