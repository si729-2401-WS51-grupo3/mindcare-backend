package pe.edu.upc.mind.care.platform.NoteManagement.interfaces.rest.resources;

public record AppointmentNoteResource(Long noteId, String psychologistNotes, Long appointmentId, Long psychologistId, Long patientId) {}

