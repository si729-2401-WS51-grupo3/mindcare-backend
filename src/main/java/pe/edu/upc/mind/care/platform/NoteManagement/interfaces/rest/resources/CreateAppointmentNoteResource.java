package pe.edu.upc.mind.care.platform.NoteManagement.interfaces.rest.resources;

public record CreateAppointmentNoteResource(String psychologistNotes, Long appointmentId, Long psychologistId, Long patientId) {}

