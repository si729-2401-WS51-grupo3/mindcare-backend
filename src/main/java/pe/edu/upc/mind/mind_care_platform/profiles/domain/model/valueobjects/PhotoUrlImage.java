package pe.edu.upc.mind.mind_care_platform.profiles.domain.model.valueobjects;

public record PhotoUrlImage(String url) {
    public PhotoUrlImage {
        if (url == null) {
            throw new IllegalArgumentException("Photo url can't be null");
        }
        if (url.isBlank()) {
            throw new IllegalArgumentException("Photo url can't be empty");
        }
    }
}
