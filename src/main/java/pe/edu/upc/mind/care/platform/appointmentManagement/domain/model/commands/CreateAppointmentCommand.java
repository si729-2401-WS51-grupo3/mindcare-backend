package pe.edu.upc.mind.care.platform.appointmentManagement.domain.model.commands;

import pe.edu.upc.mind.care.platform.appointmentManagement.domain.model.valueobjects.PsychologistId;
import pe.edu.upc.mind.care.platform.appointmentManagement.domain.model.valueobjects.Type;
import pe.edu.upc.mind.care.platform.appointmentManagement.domain.model.valueobjects.PatientId;

public record CreateAppointmentCommand(String title, String description, java.util.Date date, Type type, int hour, PsychologistId psychologistId, PatientId patientId) {
}
