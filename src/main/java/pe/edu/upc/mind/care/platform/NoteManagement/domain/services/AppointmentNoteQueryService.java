package pe.edu.upc.mind.care.platform.NoteManagement.domain.services;
import pe.edu.upc.mind.care.platform.NoteManagement.domain.model.aggregates.AppointmentNote;
import pe.edu.upc.mind.care.platform.NoteManagement.domain.model.queries.GetAllAppointmentNotesQuery;
import pe.edu.upc.mind.care.platform.NoteManagement.domain.model.queries.GetAppointmentNoteByIdQuery;
import pe.edu.upc.mind.care.platform.NoteManagement.domain.model.queries.GetNotesByPatientIdQuery;
import pe.edu.upc.mind.care.platform.NoteManagement.domain.model.queries.GetNotesByPsychologistIdQuery;

import java.util.List;
import java.util.Optional;


public interface AppointmentNoteQueryService {
    List<AppointmentNote> handle(GetAllAppointmentNotesQuery query);
    Optional<AppointmentNote> handle(GetAppointmentNoteByIdQuery query);
    List<AppointmentNote> handle(GetNotesByPsychologistIdQuery query);
    List<AppointmentNote> handle(GetNotesByPatientIdQuery query);
}
