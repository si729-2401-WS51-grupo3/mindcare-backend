package pe.edu.upc.mind.mind_care_platform.appointmentManagement.domain.model.commands;

import pe.edu.upc.mind.mind_care_platform.appointmentManagement.domain.model.aggregates.Appointment;

public record AddAppointmentDetailToAppointmentCommand(Long appointmentId, String description) {
}
