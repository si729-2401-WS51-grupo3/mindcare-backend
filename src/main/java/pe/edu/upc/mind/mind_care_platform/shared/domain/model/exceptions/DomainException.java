package pe.edu.upc.mind.mind_care_platform.shared.domain.model.exceptions;

public class DomainException extends RuntimeException {
    public DomainException(String message) {
        super(message);
    }
}