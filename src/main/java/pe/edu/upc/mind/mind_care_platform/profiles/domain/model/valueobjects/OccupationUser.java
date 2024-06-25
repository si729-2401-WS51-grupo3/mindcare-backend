package pe.edu.upc.mind.mind_care_platform.profiles.domain.model.valueobjects;

public record OccupationUser(String occupation) {
    public OccupationUser() {
        this(null);
    }
}
