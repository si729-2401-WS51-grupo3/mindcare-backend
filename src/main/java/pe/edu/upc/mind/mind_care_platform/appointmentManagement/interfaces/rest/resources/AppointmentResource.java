package pe.edu.upc.mind.mind_care_platform.appointmentManagement.interfaces.rest.resources;

public record AppointmentResource(Long id, String title, String description, String date, String type, int hour, Long psychologistId, Long patientId) {
}
