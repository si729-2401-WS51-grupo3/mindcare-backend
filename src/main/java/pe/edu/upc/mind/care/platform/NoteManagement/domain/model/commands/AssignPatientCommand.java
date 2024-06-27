package pe.edu.upc.mind.care.platform.NoteManagement.domain.model.commands;


import pe.edu.upc.mind.care.platform.NoteManagement.domain.model.valueobjects.PatientId;

public record AssignPatientCommand(Long appointmentNoteId, PatientId patientId) {}

