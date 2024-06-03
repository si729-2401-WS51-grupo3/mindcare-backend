package pe.edu.upc.mind.mind_care_platform.NoteManagement.domain.model.commands;

import pe.edu.upc.mind.mind_care_platform.NoteManagement.domain.model.valueobjects.PsychologistId;
import pe.edu.upc.mind.mind_care_platform.NoteManagement.domain.model.valueobjects.PatientId;
import pe.edu.upc.mind.mind_care_platform.NoteManagement.domain.model.entities.Appointment;
import pe.edu.upc.mind.mind_care_platform.NoteManagement.domain.model.entities.Note;

public record CreateAppointmentNoteCommand(PsychologistId psychologistId, PatientId patientId, Appointment appointment, Note note) {}
