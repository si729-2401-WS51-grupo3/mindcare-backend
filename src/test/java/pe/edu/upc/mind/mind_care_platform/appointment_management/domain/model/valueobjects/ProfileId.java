package pe.edu.upc.mind.mind_care_platform.appointment_management.domain.model.valueobjects;
import jakarta.persistence.Embeddable;

/**
 * Value object representing the profile id.
 */
@Embeddable
public record ProfileId(Long profileId) {

    public ProfileId {
        if (profileId < 0) {
            throw new IllegalArgumentException("Profile profileId cannot be negative");
        }
    }

    public ProfileId() {
        this(0L);
    }
}
