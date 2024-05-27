package pe.edu.upc.mind.mindcare_platform.therapymanagement.domain.model.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import pe.edu.upc.mind.mindcare_platform.therapymanagement.domain.model.valueobjects.MeetingStatus;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
public class Meeting {
    @Setter
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Setter
    @Getter
    private String name;
    @Setter
    @Getter
    private LocalDate date;
    @Setter
    @Getter
    private LocalTime hour;

    @Getter
    @ManyToOne
    //Aca se debe cambiar el nombre de la columna a psychologist_id
    @JoinColumn(name = "psychologist_id")
    private Psychologist psychologist;

    @ManyToOne
    //Aca se debe cambiar el nombre de la columna a patient_id
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @Setter
    @Getter
    //el enumerated sirve para que se guarde en la base de datos como un string
    @Enumerated(EnumType.STRING)
    //aca llama al value object
    private MeetingStatus status;

}
