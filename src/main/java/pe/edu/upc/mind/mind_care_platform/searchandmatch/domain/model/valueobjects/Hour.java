package pe.edu.upc.mind.mind_care_platform.searchandmatch.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public class Hour {
    private int hour;
    private HourStatus status;

    public Hour(int hour, HourStatus status) {
        this.hour = hour;
        this.status = status;
    }

    public Hour() {

    }

    // getters and setters
    public int getHour() {
        return this.hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public HourStatus getStatus() {
        return this.status;
    }

    public void setStatus(HourStatus status) {
        this.status = status;
    }
}