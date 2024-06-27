package pe.edu.upc.mind.care.platform.NoteManagement.domain.model.commands;

public record UpdateNoteContentCommand(Long appointmentNoteId, String content) {}

