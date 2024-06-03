package pe.edu.upc.mind.mind_care_platform.NoteManagement.domain.exceptions;

public class AppointmentNotFoundException extends RuntimeException {
    public AppointmentNotFoundException(String message) {
        super(message);
    }
}

