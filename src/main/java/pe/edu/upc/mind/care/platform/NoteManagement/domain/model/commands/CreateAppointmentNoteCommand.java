package pe.edu.upc.mind.care.platform.NoteManagement.domain.model.commands;


import pe.edu.upc.mind.care.platform.NoteManagement.domain.model.entities.Note;
import pe.edu.upc.mind.care.platform.NoteManagement.domain.model.valueobjects.AppointmentId;
import pe.edu.upc.mind.care.platform.searchandmatch.domain.model.valueobjects.PatientId;
import pe.edu.upc.mind.care.platform.searchandmatch.domain.model.valueobjects.PsychologistId;

public record CreateAppointmentNoteCommand(PsychologistId psychologistId, PatientId patientId, AppointmentId appointmentId, Note note) {}

