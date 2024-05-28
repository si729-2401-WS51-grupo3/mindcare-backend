package pe.edu.upc.mind.mind_care_platform.TherapyDesign.domain.model.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@Entity
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private Date date;
    private Date hour;
    private String notes;
    private int meetingTypeId;
    private int psychologistId;
    private int patientId;
    private int statusId;

    // Getters and Setters

}
