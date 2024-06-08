package pe.edu.upc.mind.mind_care_platform.searchandmatch.domain.model.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import pe.edu.upc.mind.mind_care_platform.searchandmatch.domain.model.aggregates.Reservation;
import pe.edu.upc.mind.mind_care_platform.searchandmatch.domain.model.commands.CreatePsychologistCommand;
import pe.edu.upc.mind.mind_care_platform.shared.domain.model.entity.AuditableModel;

public class Psychologist extends AuditableModel {
    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    String name;

    @Getter
    @Setter
    private int worked_hours;

    @Getter
    @Setter
    private int started_hour;

    @Getter
    @OneToMany
    @JoinColumn(name = "schedule_id")
    private Schedule schedule;

    @OneToMany
    private Reservation reservation;

    public Psychologist(String name, int worked_hours, int started_hour) {
        this.name = name;
        this.worked_hours = worked_hours;
        this.started_hour = started_hour;
    }

    public Psychologist(CreatePsychologistCommand command){
        this.name = command.name();
        this.worked_hours = command.worked_hours();
        this.started_hour = command.started_hour();
    }
    public Psychologist updateInformation(String name, int worked_hours, int started_hour){
        this.name = name;
        this.worked_hours = worked_hours;
        this.started_hour = started_hour;
        return this;
    }
}
