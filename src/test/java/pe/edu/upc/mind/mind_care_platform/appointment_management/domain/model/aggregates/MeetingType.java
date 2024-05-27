package pe.edu.upc.mind.mind_care_platform.appointment_management.domain.model.aggregates;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Entity
public class MeetingType {
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Setter
    private String meetingName;

    @OneToMany(mappedBy = "meetingType", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Appointment> appointments = new HashSet<>();

    // Getters and setters

    public void addAppointment(Appointment appointment) {
        appointments.add(appointment);
        appointment.setMeetingType(this);
    }

    public void removeAppointment(Appointment appointment) {
        appointments.remove(appointment);
        appointment.setMeetingType(null);
    }
}
