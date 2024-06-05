package pe.edu.upc.mind.mind_care_platform.NoteManagement.domain.model.commands;

import pe.edu.upc.mind.mind_care_platform.NoteManagement.domain.model.valueobjects.PsychologistId;

public record AssignPsychologistCommand(Long appointmentNoteId, PsychologistId psychologistId) {}

