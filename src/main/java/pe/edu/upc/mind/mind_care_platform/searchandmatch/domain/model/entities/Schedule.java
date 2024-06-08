package pe.edu.upc.mind.mind_care_platform.searchandmatch.domain.model.entities;

import jakarta.persistence.*;
import lombok.Getter;
import pe.edu.upc.mind.mind_care_platform.searchandmatch.domain.model.aggregates.Reservation;
import pe.edu.upc.mind.mind_care_platform.searchandmatch.domain.model.valueobjects.Hour;
import pe.edu.upc.mind.mind_care_platform.searchandmatch.domain.model.valueobjects.HourStatus;

import java.util.ArrayList;
import java.util.List;

public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @ManyToOne
    @JoinColumn(name = "psychologist_id")
    private Psychologist psychologist;

    List<Hour> workingHours;
    private String day;

    @OneToMany(mappedBy = "schedule")
    private List<Reservation> reservations;

    public Schedule(Psychologist psychologist, String day) {
        this.psychologist=psychologist;
        this.day=day;
        this.workingHours = new ArrayList<>();
        setWorkingHours();
    }

    public void setWorkingHours() {
        for (int i = 0; i < psychologist.getWorked_hours(); i++) {
            int currentHour = psychologist.getStarted_hour() + i;
            workingHours.add(new Hour(currentHour, HourStatus.available));
        }
    }

    public void reserveHour(int hour) {
        Hour selectedHour = workingHours.get(hour);
        if (selectedHour.getStatus() == HourStatus.reserved) {
            throw new IllegalStateException("This hour is already reserved.");
        }
        selectedHour.setStatus(HourStatus.reserved);
    }
}
