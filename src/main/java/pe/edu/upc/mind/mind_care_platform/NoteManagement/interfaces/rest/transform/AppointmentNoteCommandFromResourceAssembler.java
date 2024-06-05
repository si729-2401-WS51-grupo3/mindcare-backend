package pe.edu.upc.mind.mind_care_platform.NoteManagement.interfaces.rest.transform;

import pe.edu.upc.mind.mind_care_platform.NoteManagement.domain.model.commands.CreateAppointmentNoteCommand;
import pe.edu.upc.mind.mind_care_platform.NoteManagement.domain.model.commands.UpdateNoteContentCommand;
import pe.edu.upc.mind.mind_care_platform.NoteManagement.domain.model.entities.Note;
import pe.edu.upc.mind.mind_care_platform.NoteManagement.domain.model.valueobjects.AppointmentId;
import pe.edu.upc.mind.mind_care_platform.NoteManagement.domain.model.valueobjects.PatientId;
import pe.edu.upc.mind.mind_care_platform.NoteManagement.domain.model.valueobjects.PsychologistId;
import pe.edu.upc.mind.mind_care_platform.NoteManagement.interfaces.rest.resources.CreateAppointmentNoteResource;
import pe.edu.upc.mind.mind_care_platform.NoteManagement.interfaces.rest.resources.UpdateNoteContentResource;

public class AppointmentNoteCommandFromResourceAssembler {
    public static CreateAppointmentNoteCommand toCommandFromResource(CreateAppointmentNoteResource resource) {
        return new CreateAppointmentNoteCommand(
                new PsychologistId(resource.psychologistId()),
                new PatientId(resource.patientId()),
                new AppointmentId(resource.appointmentId()),
                new Note(resource.psychologistNotes())
        );
    }

    public static UpdateNoteContentCommand toCommandFromResource(UpdateNoteContentResource resource) {
        return new UpdateNoteContentCommand(
                resource.appointmentNoteId(),
                resource.content()
        );
    }
}
