package pe.edu.upc.mind.care.platform.NoteManagement.domain.services;



import pe.edu.upc.mind.care.platform.NoteManagement.domain.model.aggregates.AppointmentNote;
import pe.edu.upc.mind.care.platform.NoteManagement.domain.model.commands.*;

import java.util.Optional;

public interface AppointmentNoteCommandService {
    AppointmentNote handle(CreateAppointmentNoteCommand command);
    Optional<AppointmentNote> handle(UpdateNoteContentCommand command);
    void handle(AssignPsychologistCommand command);
    void handle(AssignPatientCommand command);
    void handle(DeleteAppointmentNoteCommand command);

}



