package pe.edu.upc.mind.mind_care_platform.searchandmatch.domain.model.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import pe.edu.upc.mind.mind_care_platform.searchandmatch.domain.model.aggregates.Reservation;
import pe.edu.upc.mind.mind_care_platform.searchandmatch.domain.model.commands.CreateScheduleCommand;
import pe.edu.upc.mind.mind_care_platform.searchandmatch.domain.model.valueobjects.Hour;
import pe.edu.upc.mind.mind_care_platform.searchandmatch.domain.model.valueobjects.HourStatus;
import pe.edu.upc.mind.mind_care_platform.searchandmatch.domain.model.valueobjects.PsychologistId;
import pe.edu.upc.mind.mind_care_platform.shared.domain.model.entity.AuditableModel;

import java.util.ArrayList;
import java.util.List;
@Getter
@Entity
public class Schedule extends AuditableModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private PsychologistId psychologistId;

    @Setter
    private int worked_hours;

    @Setter
    private int started_hour;

    @Setter
    private String day;

    @OneToMany(mappedBy = "schedule")
    private List<Reservation> reservations;

    @ElementCollection
    private List<Hour> workingHours;

    public Schedule(int worked_hours, int started_hour, String day, PsychologistId psychologistId) {
        this.worked_hours = worked_hours;
        this.started_hour = started_hour;
        this.day = day;
        this.psychologistId = psychologistId;
        this.workingHours = new ArrayList<>();
        setWorkingHours();
    }

    public Schedule(CreateScheduleCommand command) {
        this.worked_hours = command.worked_hours();
        this.started_hour = command.started_hour();
        this.day = command.day();
        this.psychologistId = command.psychologistId();
        this.workingHours = new ArrayList<>();
        setWorkingHours();
    }

    public Schedule() {

    }

    public void setWorkingHours() {
        for (int i = 0; i < worked_hours; i++) {
            int currentHour = started_hour + i;
            workingHours.add(new Hour(currentHour, HourStatus.available));
        }
    }

    public Schedule updateSchedule(int worked_hours, int started_hour, String day){
        this.worked_hours = worked_hours;
        this.started_hour = started_hour;
        this.day = day;
        return this;
    }
}
