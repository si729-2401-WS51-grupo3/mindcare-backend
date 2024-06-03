package pe.edu.upc.mind.mind_care_platform.NoteManagement.domain.services;

import pe.edu.upc.mind.mind_care_platform.NoteManagement.domain.model.aggregates.AppointmentNote;
import pe.edu.upc.mind.mind_care_platform.NoteManagement.domain.model.queries.*;

import java.util.List;
import java.util.Optional;

public interface AppointmentNoteQueryService {
    List<AppointmentNote> handle(GetAllAppointmentNotesQuery query);
    Optional<AppointmentNote> handle(GetAppointmentNoteByIdQuery query);
    List<AppointmentNote> handle(GetNotesByPsychologistIdQuery query);
    List<AppointmentNote> handle(GetNotesByPatientIdQuery query);
}
