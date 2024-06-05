package pe.edu.upc.mind.mind_care_platform.NoteManagement.domain.model.commands;

public record UpdateNoteContentCommand(Long appointmentNoteId, String content) {}

