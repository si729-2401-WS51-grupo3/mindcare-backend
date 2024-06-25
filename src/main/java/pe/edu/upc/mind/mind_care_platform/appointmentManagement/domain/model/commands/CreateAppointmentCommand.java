package pe.edu.upc.mind.mind_care_platform.appointmentManagement.domain.model.commands;

import pe.edu.upc.mind.mind_care_platform.appointmentManagement.domain.model.valueobjects.PatientId;
import pe.edu.upc.mind.mind_care_platform.appointmentManagement.domain.model.valueobjects.PsychologistId;
import pe.edu.upc.mind.mind_care_platform.appointmentManagement.domain.model.valueobjects.Type;

public record CreateAppointmentCommand(String title, String description, java.util.Date date, Type type, int hour, PsychologistId psychologistId, PatientId patientId) {
}
