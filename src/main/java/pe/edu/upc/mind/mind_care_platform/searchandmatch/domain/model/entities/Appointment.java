package pe.edu.upc.mind.mind_care_platform.searchandmatch.domain.model.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;

import java.sql.Date;

@Entity
public class Appointment {
    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private Date date;
    private String name;

    @ManyToOne
    @JoinColumn(name = "psychologist_id")
    private Psychologist psychologist;


    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

}
