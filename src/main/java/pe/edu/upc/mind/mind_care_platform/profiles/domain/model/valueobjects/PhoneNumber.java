package pe.edu.upc.mind.mind_care_platform.profiles.domain.model.valueobjects;

public record PhoneNumber(String phoneNumber) {
    public PhoneNumber {
        if (!phoneNumber.matches("^[0-9]{9}$")) {
            throw new IllegalArgumentException("Invalid phone number");
        }
    }
}
