package pe.edu.upc.mind.mind_care_platform.TherapyDesign.domain.exceptions;

public class AppointmentNotFoundException extends RuntimeException {
    public AppointmentNotFoundException(String message) {
        super(message);
    }
}

