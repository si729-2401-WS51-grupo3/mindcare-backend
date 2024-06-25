package pe.edu.upc.mind.mind_care_platform.profiles.domain.model.valueobjects;

public record DescriptionUser(String description) {
    public DescriptionUser {
        if (description == null || description.isBlank()) {
            throw new IllegalArgumentException("Description cannot be null or blank");
        }
    }
}
