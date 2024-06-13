package pe.edu.upc.mind.mind_care_platform.appointmentManagement.domain.model.commands;

import pe.edu.upc.mind.mind_care_platform.appointmentManagement.domain.model.valueobjects.PsychologistId;

public record AssignPsychologistToAppointmentCommand(Long appointmentId, PsychologistId psychologistId) {
}
