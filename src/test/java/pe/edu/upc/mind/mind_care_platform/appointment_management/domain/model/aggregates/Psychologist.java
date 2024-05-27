package pe.edu.upc.mind.mind_care_platform.appointment_management.domain.model.aggregates;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Entity
public class Psychologist {
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Setter
    private String name;
    @Setter
    private String lastName;
    @Setter
    private int phone;
    @Setter
    private String email;
    @Setter
    private String description;
    @Setter
    private int specialityId;

    @OneToMany(mappedBy = "psychologist", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Appointment> appointments = new HashSet<>();

    // Getters and setters

    public void addAppointment(Appointment appointment) {
        appointments.add(appointment);
        appointment.setPsychologist(this);
    }

    public void removeAppointment(Appointment appointment) {
        appointments.remove(appointment);
        appointment.setPsychologist(null);
    }
}
