package pe.edu.upc.mind.mind_care_platform.NoteManagement.domain.services;

import pe.edu.upc.mind.mind_care_platform.NoteManagement.domain.model.aggregates.AppointmentNote;
import pe.edu.upc.mind.mind_care_platform.NoteManagement.domain.model.commands.*;
import pe.edu.upc.mind.mind_care_platform.NoteManagement.domain.model.entities.Note;

import java.util.Optional;

public interface AppointmentNoteCommandService {
    AppointmentNote handle(CreateAppointmentNoteCommand command);
    Optional<AppointmentNote> handle(UpdateNoteContentCommand command);
    void handle(AssignPsychologistCommand command);
    void handle(AssignPatientCommand command);
    void handle(DeleteAppointmentNoteCommand command);

}



