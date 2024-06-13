package pe.edu.upc.mind.mind_care_platform.appointmentManagement.domain.model.commands;

import pe.edu.upc.mind.mind_care_platform.appointmentManagement.domain.model.valueobjects.PsychologistId;

public record UpdateAppointmentCommand(Long appointmentId , String title, String type, PsychologistId psychologistId) {
}
