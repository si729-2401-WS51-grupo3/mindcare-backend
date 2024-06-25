package pe.edu.upc.mind.mind_care_platform.profiles.domain.model.valueobjects;

public record BirthDateUser(String birthDate) {
    public BirthDateUser() {
        this(null);
    }
}
