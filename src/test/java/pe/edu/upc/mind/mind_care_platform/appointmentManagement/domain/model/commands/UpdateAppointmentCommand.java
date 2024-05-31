package pe.edu.upc.mind.mind_care_platform.appointmentManagement.domain.model.commands;
import pe.edu.upc.mind.mind_care_platform.appointmentManagement.domain.model.valueobjects.MeetingType;
import pe.edu.upc.mind.mind_care_platform.appointmentManagement.domain.model.valueobjects.PatientId;
import pe.edu.upc.mind.mind_care_platform.appointmentManagement.domain.model.valueobjects.PsychologistId;
import java.time.LocalDateTime;

public record UpdateAppointmentCommand(Long appointmentId, String sessionName, LocalDateTime date, LocalDateTime time, MeetingType meetingType, PsychologistId psychologistId, PatientId patientId) {
}
