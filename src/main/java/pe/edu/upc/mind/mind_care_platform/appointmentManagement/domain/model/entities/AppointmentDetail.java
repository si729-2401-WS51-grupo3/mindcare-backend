package pe.edu.upc.mind.mind_care_platform.appointmentManagement.domain.model.entities;

import pe.edu.upc.mind.mind_care_platform.appointmentManagement.domain.model.aggregates.Appointment;
import pe.edu.upc.mind.mind_care_platform.appointmentManagement.domain.model.valueobjects.PatientId;
import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
public class AppointmentDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "appointment_id")
    private Appointment appointment;

    private String description;

    @Embedded
    private PatientId patientId;

    public AppointmentDetail() {
        this.appointment = new Appointment();
        this.description = "";
        this.patientId = new PatientId();
    }
    public AppointmentDetail(Appointment appointment, String description) {
        this.appointment = appointment;
        this.description = description;
    }

}
