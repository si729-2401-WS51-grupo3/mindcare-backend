package pe.edu.upc.mind.mind_care_platform.therapymanagement.domain.model.valueobjects;

import jakarta.persistence.*;

/**
 * Value object representing the meeting id.
 */
@Embeddable
//bounded context de search and match
public record MeetingId(Long meetingId) {
    public MeetingId {
        if (meetingId == null) {
            throw new IllegalArgumentException("MeetingId cannot be null");
        }
    }

    public MeetingId(){
        this(null);
    }
}
