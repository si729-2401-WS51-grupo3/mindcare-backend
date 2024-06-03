package pe.edu.upc.mind.mind_care_platform.NoteManagement.interfaces.rest.resources;

public record CreateAppointmentNoteResource(String psychologistNotes, Long appointmentId, Long psychologistId, Long patientId) {}

