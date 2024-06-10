package pe.edu.upc.mind.mind_care_platform.appointmentManagement.interfaces.rest.resources;

public record CreateAppointmentResource(String sessionName, int psychologistId, int patientId) {
}