package pe.edu.upc.mind.mind_care_platform.appointmentManagement.interfaces.rest.resources;

public record UpdateAppointmentResource(Long appointmentId, String title, String type, Long psychologistId) {
}
