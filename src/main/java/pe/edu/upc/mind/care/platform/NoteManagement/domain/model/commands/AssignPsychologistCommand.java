package pe.edu.upc.mind.care.platform.NoteManagement.domain.model.commands;


import pe.edu.upc.mind.care.platform.NoteManagement.domain.model.valueobjects.PsychologistId;

public record AssignPsychologistCommand(Long appointmentNoteId, PsychologistId psychologistId) {}

