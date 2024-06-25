package pe.edu.upc.mind.mind_care_platform.profiles.domain.model.valueobjects;

public record GenderUser(String gender) {
    public GenderUser() {
        this(null);
    }
}
